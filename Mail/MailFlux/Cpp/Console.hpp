/*****************************************************************************
FILE          : Console.hpp
LAST REVISION : 2009-03-10
PROGRAMMER    : (C) Copyright 2009 by Peter C. Chapin

Interface to the console namespace. This namespace manages the console and provides coordinated
access to it.

Please send comments or bug reports to

     Peter C. Chapin
     Computer Information Systems Department
     Vermont Technical College
     Randolph Center, VT 05061
     Peter.Chapin@vtc.vsc.edu
*****************************************************************************/

#ifndef CONSOLE_HPP
#define CONSOLE_HPP

#include <string>

//! Provides all console handling functions.
/*!
 * This namespace encloses the console library. Once initialized all console I/O should go
 * through these functions. The console library uses curses. It allows multiple threads to call
 * it but it does not assume that the underlying curses library is thread safe. The console
 * library applies locks as appropriate.
 *
 * The console consists of two display areas that run concurrently. One display area is output
 * only; it is used for the asynchronous display of information about network activity and mail
 * message. The other display area is interactive. It is used for printing a prompt and
 * displaying the immediate results of commands. The interactive display area runs in its own
 * thread.
 */
namespace Console {
    void initialize();
    void cleanup();
    void put_line(const char *line);
    void put_warning_line(const char *line);
    void put_exception_line(const char *line);
    void put_debug_line(const char *line);
    void command_loop();
}

#endif

