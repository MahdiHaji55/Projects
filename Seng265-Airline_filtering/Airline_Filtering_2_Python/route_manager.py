#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
Created on Wed Feb 8 14:44:33 2023
Based on: https://www.kaggle.com/datasets/arbazmohammad/world-airports-and-airlines-datasets
Sample input: --AIRLINES="airlines.yaml" --AIRPORTS="airports.yaml" --ROUTES="routes.yaml" --QUESTION="q1" --GRAPH_TYPE="bar"
@author: rivera
@author: STUDENT_ID
"""

# Name: Mahdi Haji Hosseini


import csv
import sys
import yaml
import pandas as pd
import matplotlib.pyplot as plt
from matplotlib.backends.backend_pdf import PdfPages


def q1(AIRLINES, AIRPORTS, ROUTES):

    
    

   # Read the YAML files and convert them to dataframes
    with open(AIRLINES) as f:
        airlines_df = pd.DataFrame(yaml.safe_load(f)['airlines'])

    with open(ROUTES) as f:
        routes_df = pd.DataFrame(yaml.safe_load(f)['routes'])

    with open(AIRPORTS) as f:
        airports_df = pd.DataFrame(yaml.safe_load(f)['airports'])

    # Merge the dataframes to get the airline names for each route
    merged_df = pd.merge(routes_df, airlines_df[['airline_id', 'airline_name', 'airline_icao_unique_code']], how='left', left_on='route_airline_id', right_on='airline_id')

    # Filter the routes that have destination country as Canada
    canada_routes_df = pd.merge(merged_df, airports_df, how='left', left_on='route_to_airport_id', right_on='airport_id')
    canada_routes_df = canada_routes_df[canada_routes_df['airport_country'] == 'Canada']

    # Count the number of routes for each airline
    airline_routes_count = canada_routes_df.groupby(['airline_name', 'airline_icao_unique_code']).size().reset_index(name='route_count')

    # Add the airline code in parentheses to the airline name
    airline_routes_count['airline_name'] = airline_routes_count['airline_name'] + ' (' + airline_routes_count['airline_icao_unique_code'] + ')'

    # Sort the airlines by route count in descending order, and then by airline name
    airline_routes_count = airline_routes_count.sort_values(['route_count', 'airline_name'], ascending=[False, True])

    # Write the result to CSV file
    airline_routes_count[['airline_name', 'route_count']].head(20).to_csv('q1.csv', index=False, header=['subject', 'statistic'])

    





def q2(AIRPORTS, ROUTES):

       
    # Read the routes YAML file and convert it to a dataframe
    with open(ROUTES) as f:
        routes_df = pd.DataFrame(yaml.safe_load(f)['routes'])

    # Merge the routes dataframe with the airports dataframe to get the destination country for each route
    with open(AIRPORTS) as f:
        airports_df = pd.DataFrame(yaml.safe_load(f)['airports'])
        routes_df = pd.merge(routes_df, airports_df[['airport_id', 'airport_country']], how='left', left_on='route_to_airport_id', right_on='airport_id')

    # Get the count of routes for each destination country
    country_routes_count = routes_df.groupby('airport_country').size().reset_index(name='count')

    # Sort the countries by count in ascending order and then alphabetically in case of ties
    country_routes_count = country_routes_count.sort_values(['count', 'airport_country'], ascending=[True, True])
    country_routes_count['airport_country'] = country_routes_count['airport_country'].str.replace(' ', '_')
    country_routes_count = country_routes_count.sort_values(['count', 'airport_country'], ascending=[True, True])
    country_routes_count['airport_country'] = country_routes_count['airport_country'].str.replace('_', ' ')
    
    # Write the top 30 countries with least routes to a CSV file
    country_routes_count.head(30).to_csv('q2.csv', index=False, header=['subject', 'statistic'])

    
     

def q3(AIRLINES, AIRPORTS, ROUTES):

    
    # Read the airlines YAML file and convert it to a dataframe
    with open(AIRLINES) as f:
        airlines_df = pd.DataFrame(yaml.safe_load(f)['airlines'])

    # Read the airports YAML file and convert it to a dataframe
    with open(AIRPORTS) as f:
        airports_df = pd.DataFrame(yaml.safe_load(f)['airports'])

    # Read the routes YAML file and convert it to a dataframe
    with open(ROUTES) as f:
        routes_df = pd.DataFrame(yaml.safe_load(f)['routes'])

    # Join the routes dataframe with the airports dataframe for the "from" airport
    routes_from_df = routes_df.merge(airports_df, left_on='route_from_aiport_id', right_on='airport_id')

    # Join the resulting dataframe with the airports dataframe again for the "to" airport
    routes_from_to_df = routes_from_df.merge(airports_df, left_on='route_to_airport_id', right_on='airport_id', suffixes=('_from', '_to'))

    # Group the resulting dataframe by the destination airport and count the number of flights to each destination
    destinations_df = routes_from_to_df.groupby(['airport_name_to', 'airport_city_to', 'airport_country_to', 'airport_icao_unique_code_to']).size().reset_index(name='count')

    # Sort the results in descending order by the count of flights and select the top 10
    top_destinations_df = destinations_df.sort_values('count', ascending=False).head(10)

    # Convert the resulting dataframe to a list of formatted strings
    top_destinations_list = [f'"{row["airport_name_to"]} ({row["airport_icao_unique_code_to"]}), {row["airport_city_to"]}, {row["airport_country_to"]}",{row["count"]}' for _, row in top_destinations_df.iterrows()]

    with open('q3.csv', 'w') as f:
        f.write('subject,statistic\n')
        for lines in top_destinations_list:
            f.write("%s\n" % lines)



def q4(AIRLINES, AIRPORTS, ROUTES):

    # Read the airlines YAML file and convert it to a dataframe
    with open(AIRLINES) as f:
        airlines_df = pd.DataFrame(yaml.safe_load(f)['airlines'])

    # Read the airports YAML file and convert it to a dataframe
    with open(AIRPORTS) as f:
        airports_df = pd.DataFrame(yaml.safe_load(f)['airports'])

    # Read the routes YAML file and convert it to a dataframe
    with open(ROUTES) as f:
        routes_df = pd.DataFrame(yaml.safe_load(f)['routes'])
        
    
    # Merge the dataframes
    merged_df = pd.merge(routes_df, airlines_df, how='left', left_on='route_airline_id', right_on='airline_id')
    merged_df = pd.merge(merged_df, airports_df, how='left', left_on='route_to_airport_id', right_on='airport_id')

    # Group the data by city and country
    grouped_df = merged_df.groupby(['airport_city', 'airport_country']).size().reset_index(name='count')

    # Sort the data by count and city/country name
    grouped_df = grouped_df.sort_values(['count', 'airport_city'], ascending=[False, True])

    # Format the city/country column as desired
    grouped_df['city_country'] = grouped_df['airport_city'] + ', ' + grouped_df['airport_country']

    # Select the top 15 destination cities and save to a CSV file
    top_15_destinations = grouped_df[['city_country', 'count']].head(15)
    top_15_destinations.to_csv('q4.csv', index=False, header=['subject', 'statistic'])
        
    
    
    
    
   

    # Could Not Complete Part 5 Unfortunatly :(
    # But the rest of the code works fine 
"""

def q5(AIRLINES, AIRPORTS, ROUTES):
    
    # Read the airlines YAML file and convert it to a dataframe
    with open(AIRLINES) as f:
        airlines_df = pd.DataFrame(yaml.safe_load(f)['airlines'])

    # Read the airports YAML file and convert it to a dataframe
    with open(AIRPORTS) as f:
        airports_df = pd.DataFrame(yaml.safe_load(f)['airports'])

    # Read the routes YAML file and convert it to a dataframe
    with open(ROUTES) as f:
        routes_df = pd.DataFrame(yaml.safe_load(f)['routes'])
    
    
"""
  



def bar_grapher(QUESTION):
    
    # create the names of files
    question_file_name_csv: str = QUESTION + ".csv"
    question_file_name_pdf: str = QUESTION + ".pdf"
    
    
    # Title finder
    if question_file_name_csv == "q1.csv":
       title: str = "top 20 airlines that offer the greatest number of routes with destination country as Canada"
    
    if question_file_name_csv == "q2.csv":
       title: str = "top 30 countries with least appearances as destination country on the routes data"
       
    if question_file_name_csv == "q3.csv":
       title: str = "top 10 destination airports"
       
    if question_file_name_csv == "q4.csv":
       title: str = "top 15 destination cities"
       
    if question_file_name_csv == "q5.csv":
       title: str = "unique top 10 Canadian routes"
       
       
    # Load the CSV file
    df = pd.read_csv(question_file_name_csv)

    # Prepare the data for plotting
    x = df['subject']
    y = df['statistic']

    # Plot the data
    fig, ax = plt.subplots()
    ax.bar(x, y)
    
    #Customize the plot
    ax.set_title(title)
    ax.set_ylabel('NO. of routes offered to canada')
    ax.set_xlabel('Location of the subject')

    # Save the plot as a figure in the PDF
    with PdfPages(question_file_name_pdf) as pdf:
        pdf.savefig(fig)

    # Close the PDF file
    plt.close()
    
    



def pie_grapher(QUESTION):

    # create the names of files
    question_file_name_csv: str = QUESTION + ".csv"
    question_file_name_pdf: str = QUESTION + ".pdf"
    
    
    # Title finder
    if question_file_name_csv == "q1.csv":
       title: str = "top 20 airlines that offer the greatest number of routes with destination country as Canada"
    
    if question_file_name_csv == "q2.csv":
       title: str = "top 30 countries with least appearances as destination country on the routes data"
       
    if question_file_name_csv == "q3.csv":
       title: str = "top 10 destination airports"
       
    if question_file_name_csv == "q4.csv":
       title: str = "top 15 destination cities"
       
    if question_file_name_csv == "q5.csv":
       title: str = "unique top 10 Canadian routes"
       
       
    # Load the CSV file
    df = pd.read_csv(question_file_name_csv)
    
    # Prepare the data for plotting
    labels = df['subject']
    sizes = df['statistic']
    
    # Plot the data
    fig, ax = plt.subplots()
    ax.pie(sizes, labels=labels, autopct='%1.1f%%', startangle=90)
    
    # Customize the plot
    ax.set_title(title)
    ax.set_ylabel('statistic')
    
    # Save the plot as a figure in the PDF
    with PdfPages(question_file_name_pdf) as pdf:
        pdf.savefig(fig)

    # Close the PDF file
    plt.close()







def main():
    
    # get the command line arguments
    args = sys.argv

    # iterate over the arguments and split them by "="
    parsed_args = {}
    for arg in args[1:]:

        # remove quotes from the argument value
        arg = arg.replace('"', '')

        # remove double dashes from the argument key
        key, value = arg.replace('--', '').split('=')
        parsed_args[key] = value
    
    # access the parsed arguments by name
    
    AIRLINES: str = parsed_args.get('AIRLINES')
    
    
    AIRPORTS: str = parsed_args.get('AIRPORTS')
    
    
    ROUTES: str = parsed_args.get('ROUTES')
    
    
    QUESTION: str = parsed_args.get('QUESTION')
    
    GRAPH_TYPE: str = parsed_args.get('GRAPH_TYPE')

    # Condition checker for questions to take to the appropriate function 
    if QUESTION == "q1":

        q1(AIRLINES, AIRPORTS, ROUTES)


    if QUESTION == "q2":

        q2(AIRPORTS, ROUTES)
               

    if QUESTION == "q3":

        q3(AIRLINES, AIRPORTS, ROUTES)
        

    if QUESTION == "q4":

        q4(AIRLINES, AIRPORTS, ROUTES)
    
    """

    if QUESTION == "q5":

        q5(AIRLINES, AIRPORTS, ROUTES)
    """
    
    
    # Condition checker for graphs to take to the appropriate function 
    if GRAPH_TYPE == "bar":
        
       bar_grapher(QUESTION)
        
    

   
    if GRAPH_TYPE == "pie":
        
       pie_grapher(QUESTION)



if __name__ == '__main__':
    main()
