MailVision
==========

This folder contains MailVision, a collection of sub-projects of interest to mail administrators
and power users. The four main sub-projects here are closely related and will eventually be
tightly integrated. It is my expectation that eventually the programs will share code and other
resources (test fixtures, libraries, etc). The sub-projects are:

+ MailFlux: A mail transport agent that exposes SMTP details.

+ MailStop: A mail user agent that exposes email structure.

+ MailTalk: A mailing list manager.

+ XEmail: An XML Schema for email messages to replace RFC-5822 and MIME.

Note that MailFlux and MailStop are intended to be written in Ada but at this time MailFlux is
most mature in C++ with a significant Java implementation as well. This is because MailFlux has
been used in a network programming class at Vermont Technical College where C++ (in the past)
and Java (more recently) are the languages known to the students.

Peter C. Chapin  
PChapin@vtc.vsc.edu
