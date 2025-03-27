## CSC 370: Spring 2024

### PostgreSQL access details

* Student name: Mahdi Haji Hosseini
* Student netlink: `mahdihajihosseini`
* Student number:  V00968642
* PostgreSQL login: `c370_s048`
* PostgreSQL password: `jFzVlrV3`

---

**You must be logged into your `jhub-cosi.uvic.ca` account and running
a terminal in order to access the PostgreSQL server.**

Connection to the server is via the `psql` command:

```
psql --host=studentdb.csc.uvic.ca --user=<PostgreSQL login> --password
```

You will be prompted for the PostgreSQL password shown above. The
`--password` option takes no arguments. When you authenticate
successfully, then you will be given the PostgreSQL prompt and will
have access to your database. The name of your database is the same as
your PostgreSQL login. You do not need to do anything extra to connect
to your database.

**Remember: The `<PostgreSQL login>` is not your Netlink
credential!**

For a list of all `psql` commands, please go to:

``https://www.postgresql.org/docs/10/app-psql.html``

That webpage includes a list of the command-line arguments for
`psql` some of which you may want to use. Besides SQL commands,
additional important commands are:

* To quit `psql`: `\q`
* For a list of your tables: `\dt`
* For a list of your views: `\di`
* To read in a file of SQL commands: `\i <filename>`
* For help: `\h`

Remember: The backslash-leading `psql` commands are different from SQL
commands. **You need not be a master of `psql` commands in order to
complete your assignments.** Make sure you learn only as much of
`psql` as you need!

In order to produce the file required for some course assignments,
you will use a program different than `psql`. `pg_dump` produces 
output that can be used to reconstruct a database (i.e., it
saves the state of the database in a way the state can be
later recovered). You will need to redirect the command’s output to a
file; that file becomes part of what you will submit. For example, here is
user `c370_s068` typing the command to create the required file:
```
pg_dump --user=c370_s068 --host=student.db.csc.uvic.ca     --password > some_file.sql
```
and the password will be prompted from the user who typed in this
command. In the example above, the commands needs to reconstruct the
database (tables, contents of tables, views, etc.) associated with
user `c370_s68` will be output and saved the
file named `some_file.sql`.  **(And please do follow the assignment
directions with respect to the actual names required of any files
provided in an assignment submission. Making up filenames may result
in a failing mark for the assignment.)**

