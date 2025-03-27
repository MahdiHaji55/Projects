/* a4.c
 * CSC Fall 2022
 * 
 * Student name: mahdi haji hosseini
 * Student UVic ID: V00968642
 * Date of completed work: 12/4/2023
 *
 *
 * Code provided for Assignment #4
 *
 * Author: Mike Zastre (2022-Nov-22)
 *
 * This skeleton of a C language program is provided to help you
 * begin the programming tasks for A#4. As with the previous
 * assignments, there are "DO NOT TOUCH" sections. You are *not* to
 * modify the lines within these section.
 *
 * You are also NOT to introduce any new program-or file-scope
 * variables (i.e., ALL of your variables must be local variables).
 * YOU MAY, however, read from and write to the existing program- and
 * file-scope variables. Note: "global" variables are program-
 * and file-scope variables.
 *
 * UNAPPROVED CHANGES to "DO NOT TOUCH" sections could result in
 * either incorrect code execution during assignment evaluation, or
 * perhaps even code that cannot be compiled.  The resulting mark may
 * be zero.
 */


/* =============================================
 * ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
 * =============================================
 */

#define __DELAY_BACKWARD_COMPATIBLE__ 1
#define F_CPU 16000000UL

#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>

#define DELAY1 0.000001
#define DELAY3 0.01

#define PRESCALE_DIV1 8
#define PRESCALE_DIV3 64
#define TOP1 ((int)(0.5 + (F_CPU/PRESCALE_DIV1*DELAY1))) 
#define TOP3 ((int)(0.5 + (F_CPU/PRESCALE_DIV3*DELAY3)))

#define PWM_PERIOD ((long int)500)

volatile long int count = 0;
volatile long int slow_count = 0;


ISR(TIMER1_COMPA_vect) {
	count++;
}


ISR(TIMER3_COMPA_vect) {
	slow_count += 5;
}

/* =======================================
 * ==== END OF "DO NOT TOUCH" SECTION ====
 * =======================================
 */


/* *********************************************
 * **** BEGINNING OF "STUDENT CODE" SECTION ****
 * *********************************************
 */

void led_state(uint8_t LED, uint8_t state) {

	DDRL = 0xFF; // port LED
	
	switch (LED) { // doing switch method
		
		
		case 0:
		
		if (state > 0) { // if greater than 0 store led pattern
			
			PORTL |= 0b10000000; 
			break;
			
		}

		PORTL &= ~(0b10000000); // store bit representation
		break;

		case 1:      
		
		if (state > 0) { // if greater than 0 store led pattern
			
			PORTL |= 0b00100000; 
			break;
		}

		PORTL &= ~(0b00100000); // store bit representation
		break;

		case 2:
		
		if (state > 0) { 
			
			PORTL |= 0b00001000; // if greater than 0 store led pattern
			break;
			
		}

		PORTL &= ~(0b00001000); // store bit representation
		break;
		
		case 3:
		
		if (state > 0) { // if greater than 0 store led pattern
			
			PORTL |= 0b00000010; 
			break;
		}

		PORTL &= ~(0b00000010); // store bit representation
		break;
	}


	
}



void SOS() {
    uint8_t light[] = {
        0x1, 0, 0x1, 0, 0x1, 0,
        0xf, 0, 0xf, 0, 0xf, 0,
        0x1, 0, 0x1, 0, 0x1, 0,
        0x0
    };

    int duration[] = {
        100, 250, 100, 250, 100, 500,
        250, 250, 250, 250, 250, 500,
        100, 250, 100, 250, 100, 250,
        250
    };

	
	
	int length = 19; // num of element in the led array
	DDRL = 0xFF;

	for (int i = 0; i < length; i++) {
		
		if (light[i] == 0xf) { // checking condition
			
			PORTL |= 0b10101010; // store bit representation
			
		}
		
		else if (light[i] == 0x1) { // checking condition
			
			PORTL = 0b10000000; // store bit representation
			
		}
		
		else { // if none
			
			PORTL = 0b00000000; // store bit representation
			
		}
		
		_delay_ms(duration[i]); // delaying
		
	}
	
	
	
	
}


void glow(uint8_t LED, float brightness) {
	
	
	
	int threshold_value = PWM_PERIOD * brightness; // initialization of threshold 
	
	for (;;) {  // looping to find conditions
		
		if (count < threshold_value && PORTL == 0) { // checking condition of threshold
			
			led_state(LED, 1); //calling to store led and display
			
		}
		
		else if (count < PWM_PERIOD && PORTL != 0) { // checking condition of threshold
			
			led_state(LED, 0); //calling to store led and display
			
		}
		
		else if (count > PWM_PERIOD) { // checking condition of threshold
			
			count = 0; 
			led_state(LED, 1); //calling to store led and display
			
		}
	}
	
	
	

}



void pulse_glow(uint8_t LED) {
	
	
	
	int incr = 1; 
	int threshold_val = 0;					// initialization of threshold

	for (;;) { // looping to find conditions to toggle threshold
		
		

		if (threshold_val >= PWM_PERIOD) { // checking condition of threshold
			
			incr = 0;
			
		}
		
		if (threshold_val <= 0) { // checking condition of threshold
			
			incr= 1;
			
		}

		if (incr <= 0 && slow_count > 5) { // checking condition of threshold and value of increment
			
			threshold_val--;
			slow_count = 0;
			
		}

		if (incr >= 1 && slow_count > 5) { // checking condition of threshold and value of increment
			
			threshold_val++;
			slow_count = 0;
			
		}
	

		// now we toggle LED by checking condition of threshold and calling to display the LED

		if (count < threshold_val && PORTL == 0) {
			
			led_state(LED, 1);
			
		}
		
		else if (count < PWM_PERIOD && PORTL != 0) {
			
			led_state(LED, 0);
			
		}

		else if (count > PWM_PERIOD) {
			
			count = 0;
			led_state(LED, 1);
			
		}
	}
	
	
	

}


void light_show() {
	
	
	
	DDRL = 0xFF;
	
	uint8_t light[] = { // initialization of the array containing the possible outcomes of the LED we need to display
		
		0b10101010, 0, 0b10101010, 0, 0b10101010, 0, //zigzag lights
		0b00101000, 0, // Middle part
		0b10000010, 0, // end part
		0b10101010, 0, 0b10101010, 0, 0b10101010, 0, //zigzag lights
		0b10000010, 0, // end part
		0b00101000, 0, // Middle part

		// left to right  
		0b00000010, 0b00001010, 0b00101000, 0b10100000, 0b10000000,
		// right to left 
		0b10000000, 0b10100000, 0b00101000, 0b00001010,
		// left to right 
		0b00000010, 0b00001010, 0b00101000, 0b10100000, 0b10000000,
		// Middle 
		0b10100000, 0b00101000, 0,
		// zebra patterns
		0b10101010, 0, 0b10101010, 0,
		// last middle pattern
		0b00101000, 0, 0b00101000, 0, 0b00101000, 0
	};
	
	
	int duration[] = { // // initialization of the array containing the possible outcomes of the delays we need to display the LED
		200, 200, 200, 200, 200, 200, 200, 200,
		200, 200, 200, 200, 200, 200, 200, 200,
		200, 200, 200, 200, 100, 100, 100, 100,
		100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
		100, 100, 100, 200, 200, 200, 200, 200,
		200, 200
	};

	int length_lgt = 47; // num of element in the led array
	

	for (int i = 0; i < length_lgt; i++) { // looping for condition search
		
		if (light[i] == 0b10101010) { //checking condition 
			
			PORTL |= 0b10101010; // and storing based on the criteria
			
		}
		
		else if (light[i] == 0b10100000) { //...
			
			PORTL = 0b10100000; //...
			
		}
		
		else if (light[i] == 0b10000010) { //...
			
			PORTL = 0b10000010; //...
			
		}

		else if (light[i] == 0b10000000) { //...
			
			PORTL = 0b10000000; //...
			
		}
		
		else if (light[i] == 0b00101000) { //...
			
			PORTL = 0b00101000; //...
			
		}
		
		else if (light[i] == 0b00001010) { //...
			
			PORTL = 0b00001010; //...
			
		}

		else if (light[i] == 0b00000010) { //...
			
			PORTL = 0b00000010; //...
			
		}
		
		else { //...
			
			PORTL = 0b00000000; //...
			
		}
		
		
		_delay_ms(duration[i]); // delaying
		
		
	}

	PORTL = 0; // clearing PORTL
	

}


/* ***************************************************
 * **** END OF FIRST "STUDENT CODE" SECTION **********
 * ***************************************************
 */


/* =============================================
 * ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
 * =============================================
 */

int main() {
    /* Turn off global interrupts while setting up timers. */

	cli();

	/* Set up timer 1, i.e., an interrupt every 1 microsecond. */
	OCR1A = TOP1;
	TCCR1A = 0;
	TCCR1B = 0;
	TCCR1B |= (1 << WGM12);
    /* Next two lines provide a prescaler value of 8. */
	TCCR1B |= (1 << CS11);
	TCCR1B |= (1 << CS10);
	TIMSK1 |= (1 << OCIE1A);

	/* Set up timer 3, i.e., an interrupt every 10 milliseconds. */
	OCR3A = TOP3;
	TCCR3A = 0;
	TCCR3B = 0;
	TCCR3B |= (1 << WGM32);
    /* Next line provides a prescaler value of 64. */
	TCCR3B |= (1 << CS31);
	TIMSK3 |= (1 << OCIE3A);


	/* Turn on global interrupts */
	sei();

/* =======================================
 * ==== END OF "DO NOT TOUCH" SECTION ====
 * =======================================
 */


/* *********************************************
 * **** BEGINNING OF "STUDENT CODE" SECTION ****
 * *********************************************
 */

/* This code could be used to test your work for part A. */

	led_state(0, 1);
	_delay_ms(1000);
	led_state(2, 1);
	_delay_ms(1000);
	led_state(1, 1);
	_delay_ms(1000);
	led_state(2, 0);
	_delay_ms(1000);
	led_state(0, 0);
	_delay_ms(1000);
	led_state(1, 0);
	_delay_ms(1000);
	
 

/* This code could be used to test your work for part B.

	SOS();
 */

/* This code could be used to test your work for part C.


	 glow(2, .01);
	// glow(2, .05);
	// glow(2, .09);
	
	
	
	*/



/* This code could be used to test your work for part D.

	pulse_glow(3);
 */


/* This code could be used to test your work for the bonus part.

	light_show();
	*/
 

/* ****************************************************
 * **** END OF SECOND "STUDENT CODE" SECTION **********
 * ****************************************************
 */
}
