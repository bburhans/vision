/*****************************************************************************
FILE          : Message.cpp
LAST REVISION : 2009-03-01
PROGRAMMER    : (C) Copyright 2009 by Peter C. Chapin

Implementation of a class that abstracts email messages.

Please send comments or bug reports to

     Peter C. Chapin
     Computer Information Systems Department
     Vermont Technical College
     Randolph Center, VT 05061
     Peter.Chapin@vtc.vsc.edu
*****************************************************************************/

#include "Message.hpp"


//! Erases the sender, recipient list, and message text for this Message object.
/*!
 * The object can be reused after a call to clear(). Note that the constructor of this class
 * also clears the object as if a call to this method is made.
*/
void Message::clear()
{
    sender.clear();
    recipients.clear();
    text.clear();
}

