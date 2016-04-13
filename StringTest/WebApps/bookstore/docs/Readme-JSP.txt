Introduction:

This document contains instructions on how to configure and install 
the downloaded JSP application. It assumes that:

1. You have some basic knowledge of server and database technologies. 

2. You have a web server where the application will be deployed and if 
   applicable, a database server as well.

3. Your server supports specifications of JSP 1.1 and Servlet 2.2



When you download the application, you should find the following components 
contained in the zip archive:
 
1. Language specific script files and html template files if you downloaded 
   the template version.
2. A database file or a SQL script file that can be used to recreate the
database. 
3. A folder containing images if applicable.

Installation:

The installation process is pretty straightforward and requires minimal 
adjustment of the application files. 
Proceed as follows:

1. Unzip the files into a folder within your web server hierarchy from where
the 
application will be served. Ensure that the folder name does not have spaces
in it. 
During the process of unzipping, make sure that the files are unzipped to
their 
respective folders. Don't simply open the zip archive and drag all the files
to the 
same folders. For the application to work correctly, some files such as the
image 
files need to be in specific folders.

2. Once you have unzipped the files, the next task is to alter the database
connection 
string to reflect the current location/name of the database. Follow the
relevant 
instructions below depending on the type of connection that you want to use:

ODBC Connection

To Configure an ODBC connection:

(a) Use the ODBC option in Control Panel to setup a system DSN for the
application 
database. The database file is located in the main folder of the
application. In the 
interest of security, you can and are encouraged to move the database file
to a more 
secure location outside the web server hierarchy. Your application will work
fine as 
long as the DSN you configure points to the correct location of the database
file. 
Ensure that the DSN is a system DSN so that it will be available to all
users.

(b) Open the file 'Common.jsp' which is in the main folder of your
application path.

(c) Find the database connection strings in the file. Their basic format is:

static final String DBDriver  ="<DriverName>";
static final String strConn   ="<DBUrl>"; 

(d) Using the guidelines below, change the statement to look something like
the example 
shown below:

static final String DBDriver  ="sun.jdbc.odbc.JdbcOdbcDriver";
static final String strConn   ="jdbc:odbc:<DSN_name> "; 


where:

sun.jdbc.odbc.JdbcOdbcDriver - This is the bridge JDBC-ODBC used for the
work with databases via ODBC.

jdbc:odbc  - This is the prefix for the database connection via ODBC. 

<DSN_name> - This is the name of the ODBC DSN you created in Control Panel.

(e) If you have configured authentication for your database, locate the
following two 
statements below the connection string statement and set their values
accordingly:

static final String DBusername="";
static final String DBpassword="";

  

JDBC Connection

  
(a) You need to have the JDBC driver for the used database. The list of the
allowable
    drivers can found on the database producer site or at the site of the
Sun Corporation:
    http://industry.java.sun.com/products/jdbc/drivers. 
(b) Open the file 'Common.jsp' which is in the main folder of your
application path.
(c) In the documentation for the selected driver find the usage section. 
(d) Find the database connection strings in the file. Its basic format is:

static final String DBDriver  ="<DriverName>";
static final String strConn   ="<DBUrl>"; 

 where,

  DriverName - This is the name of the class-driver
  DBUrl - a database url of the form jdbc:subprotocol:subname
  subprotocol & subname - This is defined according to the documentation
ofthe used driver.

(e)Change the database connection strings as the following. 

(f) If you have configured authentication for your database, locate the
following two 
    statements below the connection string statement and set their values
accordingly:

static final String DBusername="";
static final String DBpassword="";

  where,

  DBusername - This is the user name for the database connection
  Dbpassword - This is the password for the database connection
