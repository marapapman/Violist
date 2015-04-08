DROP DATABASE if exists bookstore;
CREATE DATABASE bookstore;

GRANT ALL PRIVILEGES ON bookstore.* TO 'bkUser'@'localhost' IDENTIFIED BY 'bkPass' WITH GRANT OPTION;

USE bookstore;

CREATE TABLE card_types (
       card_type_id         int auto_increment primary key,
       name                 varchar(50) NOT NULL
);

insert into card_types (name) values ('Visa');
insert into card_types (name) values ('American Express');

CREATE TABLE categories (
       category_id          int  auto_increment primary key,
       name                 varchar(50) NOT NULL
);

insert into categories (name) values ('Programming');
insert into categories (name) values ('Databases');
insert into categories (name) values ('HTML & Web design');

CREATE TABLE editorial_categories (
       editorial_cat_id     int  auto_increment primary key,
       editorial_cat_name   varchar(50) NULL
);

insert into editorial_categories (editorial_cat_name) values ('What');
insert into editorial_categories (editorial_cat_name) values ('New');
insert into editorial_categories (editorial_cat_name) values ('Weekly');
insert into editorial_categories (editorial_cat_name) values ('General');

CREATE TABLE editorials (
       article_id           int auto_increment primary key,
       editorial_cat_id     int NULL DEFAULT 0,
       article_title        varchar(200) NULL,
       article_desc         text NULL,
       item_id              int NULL DEFAULT 0
);

insert into editorials (editorial_cat_id, article_title, article_desc, item_id)
       values(1, 'A Sharp Combination', 'To get inside C#, Microsoft''s new OO programming language, use A Preview of C# as a guide. It offers a preview of Visual Studio.NET and an overview of the .NET framework, and demonstrates how C# is integrated with ASP+, ADO+, and COM+ in .NET applications. You''ll get examples of C# in action, too.', 22);
insert into editorials (editorial_cat_id, article_title, article_desc, item_id)
       values(2, '1001 Web Site Construction Tips and Tricks', '39.95', 21);
insert into editorials (editorial_cat_id, article_title, article_desc, item_id)
       values(3, 'Flash 4 Magic', 'If you''re right in the middle of learning (or just jumping into) Flash to create and manipulate animations, music tracks, sound effects, and interface design, try the Flash 4 Magic. Inside are tutorials, graphic presentations, and a CD.', 8);
insert into editorials (editorial_cat_id, article_title, article_desc, item_id)
       values(4, '<b><font color="brown">Free Shipping on orders over $40</font></b>', 'For limited time only, until next Sunday, you can enjoy free shipping. Simply order more then $40 worth of books and shipping''s on us.', 0);

CREATE TABLE items (
       item_id              int auto_increment primary key,
       category_id          int NOT NULL,
       name                 varchar(255) NOT NULL,
       author               varchar(100) NULL,
       price                float NOT NULL,
       product_url          varchar(255) NULL,
       image_url            varchar(100) NULL,
       notes                text NULL,
       is_recommended       tinyint NULL DEFAULT 0,
       rating               int default 0,
       rating_count         int default 0
);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(2, 'Web Database Development : Step by Step', 'Jim Buyens', 39.99, 'http://www.amazon.com/exec/obidos/ASIN/0735609667/yessoftware', 'images/books/0735609667.jpg', 'As Web sites continue to grow in complexity and in the volume of data they must present, databases increasingly drive their content. WEB DATABASE DEVELOPMENT FUNDAMENTALS is ideal for the beginning-to-intermediate Web developer, departmental power user, or entrepreneur who wants to step up to a database-driven Web site-without buying several in-depth guides to the different technologies involved. This book uses the clear Microsoft(r) Step by Step tutorial method to familiarize developers with the technologies for building smart Web sites that present data more easily. ', 1);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'Programming Perl (3rd Edition)', 'Larry Wall, Tom Christiansen, Jon Orwant', 39.96, 'http://www.amazon.com/exec/obidos/ASIN/0596000278/yessoftware', 'images/books/0596000278.jpg', 'Perl is a powerful programming language that has grown in popularity since it first appeared in 1988. The first edition of this book, Programming Perl, hit the shelves in 1990, and was quickly adopted as the undisputed bible of the language. Since then, Perl has grown with the times, and so has this book.
Programming Perl is not just a book about Perl. It is also a unique introduction to the language and its culture, as you might expect only from its authors. Larry Wall is the inventor of Perl, and provides a unique perspective on the evolution of Perl and its future direction. Tom Christiansen was one of the first champions of the language, and lives and breathes the complexities of Perl internals as few other mortals do. Jon Orwant is the editor of The Perl Journal, which has brought together the Perl community as a common forum for new developments in Perl. ', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'Perl and CGI for the World Wide Web: Visual QuickStart Guide', 'Elizabeth Castro', 15.19, 'http://www.amazon.com/exec/obidos/ASIN/020135358X/yessoftware', 'images/books/020135358X.gif', 'Taking a visual approach, this guide uses ample screen stills to explain the basic components of Perl, and show how to install and customize existing CGI scripts to build interactivity into Web sites.', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(2, 'MySQL (OTHER NEW RIDERS)', 'Paul DuBois', 39.99, 'http://www.amazon.com/exec/obidos/ASIN/0735709211/yessoftware', 'images/books/0735709211.jpg', 'In MySQL, Paul DuBois provides you with a comprehensive guide to one of the most popular relational database systems. As an important contributor to the online documentation for MySQL, Paul uses his day-to-day experience answering questions users post on the MySQL mailing list to pinpoint the problems most users and administrators encounter.', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'PHP and MySQL Web Development', 'Luke Welling, Laura Thomson', 39.99, 'http://www.amazon.com/exec/obidos/ASIN/0672317842/yessoftware', 'images/books/0672317842.jpg', 'PHP3 and MySQL Web Development introduces you to the advantages of implementing both MySQL and PHP3. These advantages are detailed through the provision of both statistics and several case studies. A practical web application is developed throughout the book, providing you with the tools necessary to implement a functional online database. Each function is developed separately, allowing you the choice to incorporate only those parts that you would like to implement. Programming concepts of the PHP3 language are highlighted, including functions which tie MySQL support into a PHP3 script and advanced topics regarding table manipulation.', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'MySQL & PHP From Scratch', 'Wade Maxfield', 23.99, 'http://www.amazon.com/exec/obidos/ASIN/0789724405/yessoftware', 'images/books/0789724405.jpg', 'Apache, MySQL, PHP3, and IMP the pieces exist. Each piece has been described ad nauseam. What does not exist is a guide to the marriage of these software technologies into a useful book, until now. This book puts together information on installing, setting up, and troubleshooting each of these technologies into one complete volume. You also learn how each piece is part of a whole by learning, step-by-step, how to create a web-based e-mail system. Learn to run the Linux equivalent of Active Server Pages (ASP) using PHP3, set up an e-commerce site using a database and the Apache web server, and create a data entry system (such as sales, product quality tracking, customer preferences, etc) that requires no installation in the PC.', 1);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(2, 'MySQL and mSQL', 'Randy Jay Yarger, George Reese, Tim King', 27.96, 'http://www.amazon.com/exec/obidos/ASIN/1565924347/yessoftware', 'images/books/1565924347.jpg', 'MySQL and mSQL are popular and robust database products that support key subsets of SQL on both Linux and UNIX systems. Both products are free for non-profit use and cost a small amount for commercial use. 

Even a small organization or Web site has uses for a database. Prehaps you keep track of all your customers and find that your information is outgrowing the crude, flat-file format you started with. Or you want to ask your Web site''s visitors for their interests and preferences and put up a fresh Web page that tallies the results. 

Unlike commercial databases, MySQL and mSQL are affordable and easy to use. If you know basic C, Java, Perl, or Python, you can quickly write a program to interact with your database. In addition, you can embed queries and updates right in an HTML file so that a Web page becomes its own interface to the database.', 1);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(3, 'Flash 4 Magic (WITH CD-ROM)', 'David J. Emberton, J. Scott Hamlin, David Emberton', 36, 'http://www.amazon.com/exec/obidos/ASIN/0735709491/yessoftware', 'images/books/0735709491.jpg', 'Flash has moved beyond Java and even Shockwave because it''s inexpensive, accessible, and powerful. If you need a specific Flash solution or you learn best by getting your hands dirty, the project-based Flash 4 Magic will work well for you. The authors have assembled 15 utilities-based projects (a calculator and an online book search animation, for example) and six games (one of which is a memory/match game similar to those found all over the Web these days). Each project is presented step by step and includes text and complementary screen shots. At the end of each lesson, a "How It Works" section gives insight into the software mechanisms and serves as a review. A brief discussion of how the project can be modified to fit the readers'' needs follows. All the files, libraries, images, and even finished Flash .fla files and .swf movies are provided on the accompanying CD-ROM, helping readers follow along and debug their multimedia experiments.', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'Web Application Development with PHP 4.0 (with CD-ROM)', 'Tobias Ratschiller, Till Gerken', 36, 'http://www.amazon.com/exec/obidos/ASIN/0735709971/yessoftware', 'images/books/0735709971.jpg', 'PHP is an open-source Web scripting language that''s gaining steam in the development community, especially in the Apache Web server realm. With a syntax that draws heavily on C, PHP appeals to advanced programmers who are moving to the Web from traditional software development.
Web Application Development with PHP 4.0 isn''t your run-of-the-mill language tutorial. Authors Ratschiller and Gerken purposely designed its content to appeal to coders who already are proficient in PHP, but in need of advanced programming techniques and high-level application-development skills. Assuming a strong programming foundation, this book can be considered a next-level PHP tutorial.', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(2, 'Beginning ASP Databases', 'John Kaufman, Thearon Willis, David Buser, Kevin Spencer, kauffman, John Kauffman', 39.99, 'http://www.amazon.com/exec/obidos/ASIN/1861002726/yessoftware', 'images/books/1861002726.jpg', 'As a tutorial, Beginning ASP Databases offers an entry point to one of the most crucial aspects of Microsoft-oriented Web development--database integration with Active Server Pages. In Beginning ASP Databases, a trio of authors covers the basics of working with databases from ASP--especially using ActiveX Database Objects (ADO).
The book is quite substantive in content but is written in a somewhat light-hearted style that makes readers new to the technology comfortable. The authors begin with a discussion of the benefits of ASP and ADO, and then explain how to configure the technology on the various flavors of Windows. From there, they show how to access databases using ADO, demystify how it all works, and focus on several key areas, including debugging, SQL, and stored procedures.', 1);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(2, 'Oracle8i Web Development', 'Bradley D. Brown, Brad Brown', 41.99, 'http://www.amazon.com/exec/obidos/ASIN/0072122420/yessoftware', 'images/books/0072122420.jpg', 'Although this title doesn''t include the "Tips & Techniques" label like some of the other Oracle Press installments do, it falls into that category. Oracle8i Web Development is a collection of diverse overviews, specific techniques, and recommendations for the wide range of interrelated Oracle tools for creating professional Web applications.
This is not a start-to-finish tutorial but rather a tool for developers already familiar with the Oracle architecture and Oracle Application Server (OAS). It is fast moving, with targeted discussions of issues ranging from hardware preparations to HTML/JavaScript/XML coding to miscellaneous development tools. There are plenty of useful tidbits in here for experienced Oracle Web coders, but those newer to the technology platform will likely find the presentation a bit choppy.

One of the most useful sections of this book is its coverage of the WebServer Generator and Oracle Designer packages. Here the reader will learn about the generated procedures and the WebServer Generator''s key preferences. Other key chapters on Perl and Java are also useful, albeit rather brief.

An appendix of useful reference Web sites does a good job of rounding out this utilitarian guide. While this title is primarily a compendium of key information and not a comprehensive manual for Web site development, it ties together content about various products that isn''t usually found in a single source. --Stephen W. Plain', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'Black Belt Web Programming Methods; Servers, Security, Databases and Sites', '', 27.96, 'http://www.amazon.com/exec/obidos/ASIN/0879304979/yessoftware', 'images/books/0879304979.gif', 'Use this book to build a robust infrastructure for powerful Web sites. Master programmers who write for Web Techniques, Dr. Dobb''s Journal, Interactivity, Data Base Management Systems, Network, and Software Development have joined forces to tackle the latest round of web programming puzzles for you. Learn airtight security procedures and hypertext transaction designs. Build databases that are easy to use. Whether you need to create distributed objects or automate Web site maintenance, these seasoned programmers explain how to do it right, and they give you the code to do the job. The companion code disk includes detailed, reusable code for each of the applications presented. Programming languages include C/C++ and Java. The book''s four main sections (Servers, Sites, Databases, and Security (illustrate generic techniques for web configuration management and for server performance tuning. You''ll learn time-saving new tips to use with database servers and applications, high performance graphics, security options, and transaction encryption.', 1);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'Web Development with Java Server Pages', 'Duane K. Fields, Mark A. Kolb', 35.96, 'http://www.amazon.com/exec/obidos/ASIN/1884777996/yessoftware', 'images/books/1884777996.jpg', 'Dynamic content-personalized, customized, and up-to-the-minute-is a key component of site development on the World Wide Web today. Java Server Pages (JSP) is a new server-side technology for generating dynamic content in Web pages and other on-line applications. This guide to JSP covers all aspects of development and includes comparisons to similar dynamic content systems such as CGI, Active Server Pages, Cold Fusion, and PHP. Included is a discussion of the use of component centric design via Java Beans and custom tag libraries for separating the presentation of dynamic data (the layout, look, and feel) from its implementation (the code that generates it).', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'Professional Java Server Programming J2EE Edition', 'Wrox Multi Team', 47.99, 'http://www.amazon.com/exec/obidos/ASIN/1861004656/yessoftware', 'images/books/1861004656.jpg', 'Rather than a simple update of the existing Professional Java Server Programming book, the J2EE edition represents an evolution of the content to reflect the changing state of server-side Java development. Whereas the first edition can be seen as an introduction to Java on the server, the new edition is a more tightly integrated vision of how to combine the Java technologies to develop n-tier applications in Java based primarily around J2EE. Since the release of the first edition in the fall of ''99, probably the single most significant change in the Java server-side landscape has been the release of the Java 2 Platform, Enterprise Edition (J2EE). Although we covered many of the elements of J2EE in the first edition of the book, many things have changed.
J2EE represents a serious attempt by Sun to make Java not just a viable language, but more importantly a viable platform for enterprise development. This book is about how to use Java for enterprise development, using the J2EE runtime architecture. 
Wide range of technologies including: J2EE, RMI, JDBC, JNDI, LDAP, XML, XSLT, Servlets, JSP, EJB, JMS, JavaMail, CORBA, Performance, Scalability, Unit Testing, and Debugging 
Benefits and limits of the typical real-world vendor implementations of the J2EE specification 
The resulting practical aspects of real-word design using the J2EE technologies', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'Mastering ColdFusion 4.5', 'Arman Danesh, Kristin Aileen Motlagh, Kristin Motlagh', 39.99, 'http://www.amazon.com/exec/obidos/ASIN/0782127738/yessoftware', 'images/books/0782127738.jpg', 'Allaire''s ColdFusion is one of the most robust server-side scripting solutions for Web developers, offering the notable advantage of cross-platform deployment. Mastering ColdFusion 4.5 is a comprehensive exploration of this powerful product that makes migration to the language quick and first exposure to server-scripting easy to follow.
With more than 1000 pages, this book provides a step-by-step education in ColdFusion and plenty of examples for hands-on learning. The first chapters immediately give the reader a feel for how ColdFusion templates interact with HTML. Then the text steps back to begin presenting more details systematically. One of ColdFusion''s key features--database interaction with the CFQUERY tag and SQL--is addressed early on with simple examples.
Once it hooks the reader on the power of ColdFusion, the book illustrates the product''s flexibility in dealing with e-mail, FTP, LDAP servers, and custom tags. It goes on to cover the ColdFusion Studio integrated-development environment--though not in depth. The real focus of this book is how the ColdFusion language works.
Developers familiar with other scripting environments such as Microsoft Active Server Pages will immediately feel at home with the subject matter and quickly move through this hefty book. Those with only HTML knowledge will find all the details they need to learn about server-side scripting and may only need a supplemental study of SQL.', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'Teach Yourself ColdFusion in 21 Days (Teach Yourself -- 21 Days)', 'Charles Mohnike', 31.99, 'http://www.amazon.com/exec/obidos/ASIN/0672317966/yessoftware', 'images/books/0672317966.jpg', 'Sams Teach Yourself ColdFusion in 21 Days quickly empowers you to create your own dynamic database-driven Web applications using Allaire''s ColdFusion. Using client-proven methods, and the success of his popular ColdFusion tutorial for Wired, expert author Charles Mohnike provides you with an understanding of the ColdFusion Server and guides you through the use of the ColdFusion Studio, enabling you to create your own ColdFusion applications quickly and easily. Topics such as installing and configuring the ColdFusion Server, working with the ColdFusion Studio, working with SQL, optimizing your datasource, understanding templates and ColdFusion Markup Language (CFML), using ColdFusion tags, manipulating data, creating e-commerce solutions with ColdFusion, and debugging ColdFusion applications.', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'ColdFusion Fast & Easy Web Development', 'T. C., III Bradley', 19.99, 'http://www.amazon.com/exec/obidos/ASIN/0761530169/yessoftware', 'images/books/0761530169.jpg', 'Allaires ColdFusion is a powerful solution for developers wanting to build secure, scalable, and manageable Web applications. ColdFusion Fast & Easy Web Development takes a visual approach to learning this Web application server. It combines easy-to-understand instructions and real screen shots for a truly unique learning experience. This book covers topics from ColdFusion basics to retrieving data to building dynamic queries and applications with ColdFusion. The innovative, visual approach of the Fast & Easy Web Development series provides a perfect format for programmers of all levels.', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'Beginning Active Server Pages 3.0', 'David Buser, Chris Ullman, Jon Duckett…', 31.99, 'http://www.amazon.com/exec/obidos/ASIN/1861003382/yessoftware', 'images/books/1861003382.jpg', 'Filling an important spot in the Wrox Programmer to Programmer series, Beginning Active Server Pages 3.0 is an excellent introduction to the new version of ASP released for the Windows 2000 platform. This guide expects no previous ASP knowledge or even previous Web development experience. 
Its friendly style makes this book welcome reading at all skill levels. The material is carefully presented to avoid losing readers who are totally new to ASP programming, yet it still provides impressive technical coverage, beginning with the very basic concepts behind ASP programming and moving forward to advanced coding techniques.
As each topic is presented, relevant screen shots and useful code snippets under the heading "How It Works" complement the text. The chapters also include step-by-step exercises to familiarize you with new techniques and tools. An extensive case study application takes you through the entire development process as well. If you''re interested in Web coding Microsoft-style, this is the right place to start.', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(3, 'Web Design in a Nutshell : A Desktop Quick Reference', 'Jennifer Niederst, Richard Koman', 23.96, 'http://www.amazon.com/exec/obidos/ASIN/1565925157/yessoftware', 'images/books/1565925157.jpg', 'Are you a print designer working on the Web? An HTML coder learning about server-side hosting for the first time? Web Design in a Nutshell has slim but whole chapters for those topics-- and everything else you can imagine.
Written in the popular "Nutshell" format, this guide is full of helpful tables and lists, making it a perfect desktop reference. The book breaks down the huge topic of Web site development into understandable, readable segments: the Web environment (browsers, displays, design principles), an in-depth guide to HTML tags, graphics manipulation and display, multimedia possibilities, and technologies for larger site management (such as Cascading Style Sheets [CSS] and XML).
While this book is certainly comprehensive, the abundance of information could be overwhelming to someone just starting out with HTML. In addition, the heart of this book is filled with technical specificity on Web page creation (for example, a section under "Graphics" is titled "GIF87a versus GIF89a). Readers looking for more conceptual explanations of Web design and layout would be better served with other titles. But for day-to-day development and maintenance, Web Design in a Nutshell is a truly well-constructed toolkit.', 1);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(3, 'HTML 4 for the World Wide Web Visual Quickstart Guide', 'Elizabeth Castro', 15.99, 'http://www.amazon.com/exec/obidos/ASIN/0201354934/yessoftware', 'images/books/0201354934.jpg', 'Whether you use a high-end authoring application like Dreamweaver, or in the most economical fashion you write your own code out in a text file, knowing your way around HTML comes in handy. HTML 4 for the World Wide Web: Visual QuickStart Guide will teach you what you need to know quickly. 
The book covers the latest specifications of HTML 4 set by the World Wide Web Consortium, from the most basic tags that place text, images, and links on the page to more complex ones that set up tables, frames, or forms. New to this fourth edition, the book provides a chapter on debugging, including browser compatibility issues, expanded sections on cascading style sheets, JavaScript, and CGI scripts for use with forms.
As with all Visual QuickStart Guides, HTML 4 features clear and concise instructions side by side with well-captioned illustrations and screen shots that show both the source code and the resulting effect on the Web page. The index is extremely detailed, making this a good reference book for intermediate users who are already familiar with basic HTML but need help with specific topics.
The book also includes extensive and useful appendices. One offers a chart that describes each tag (along with its compatibility with Netscape Navigator and/or Microsoft Internet Explorer.) Other appendices show the code for special symbols, hexadecimal equivalents for RGB colors, and a comparison chart for some of the HTML editors on the market. All in all, this is the perfect desktop reference for Web designers.', 0);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(3, '1001 Web Site Construction Tips and Tricks', 'Richard Schwartz, Kris Jamsa, D. Runnoe Connally', 39.95, 'http://www.amazon.com/exec/obidos/ASIN/1884133193/yessoftware', 'images/books/1884133193.gif', 'There is much more to creating a Web site than simply knowing HTML. Designers must understand the ins and outs of image manipulation and compression, animation through the use of GIFs, ActiveX controls, Java applets, and server-push operations. Server administrators must understand security issues that include secure transactions, digital signatures, and firewalls. Regardless of the Web-based task at hank, users will find solutions within 1001 Web Site Construction Tips. The book''s companion CD-ROM contains utilities, scripts, and applets that users can put to immediate use on their systems.', 1);

insert into items (category_id, name, author, price, product_url, image_url, notes, is_recommended)
       values(1, 'C# - Programming with the Public Beta', 'Burton Harvey, Simon Robinson, Julian Templeman, Burt Harvey, Karli Watson', 34.99, 'http://www.amazon.com/exec/obidos/ASIN/1861004877/yessoftware', 'images/books/1861004877.jpg', 'C# is a new object-oriented programming language in development from Microsoft. Based on C++ it contains features similar to those of Java. The intention is to combine the computing power of C++ with the programming ease of Visual Basic. 
C# has been created with the Internet in mind and an aim to balance power with productivity. It provides rapid web development for the C++ and C programmer. 
Forming part of the new .NET initiative, C# is designed to be used in conjunction with Microsoft''s .NET platform of products. C# makes use of XML data and SOAP in order to simplify programming, these facilities allow the user to build on existing code rather than making repeated duplications. C# is expected to make it faster and less expensive to market new products and services. Microsoft''s aim for this product is to facilitate the exchange of information and services over the Web and to enable developers to build highly portable applications.', 0);

CREATE TABLE members (
       member_id            int auto_increment primary key,
       member_login         varchar(20) NOT NULL,
       member_password      varchar(20) NOT NULL,
       member_level         int NOT NULL DEFAULT 1,
       first_name           varchar(50) NOT NULL,
       last_name            varchar(50) NOT NULL,
       email                varchar(50) NOT NULL,
       phone                varchar(50) NULL,
       address              varchar(50) NULL,
       notes                text NULL,
       card_type_id         int NULL,
       card_number          varchar(50) NULL
);

insert into members (member_login, member_password, member_level, first_name, last_name, email)
       values ('admin', 'admin', 2, 'Administrator', 'Account', 'admin@localhost');

insert into members (member_login, member_password, member_level, first_name, last_name, email)
       values ('guest', 'guest', 1, 'Guest', 'Account', 'guest@localhost');

CREATE TABLE orders (
       order_id             int auto_increment primary key,
       member_id            int NOT NULL,
       item_id              int NOT NULL,
       quantity             int NOT NULL
);
