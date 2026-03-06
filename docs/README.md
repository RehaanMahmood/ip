# Duchess

```
$$$$$$$\                      $$\                                     
$$  __$$\                     $$ |                                    
$$ |  $$ |$$\   $$\  $$$$$$$\ $$$$$$$\   $$$$$$\   $$$$$$$\  $$$$$$$\
$$ |  $$ |$$ |  $$ |$$  _____|$$  __$$\ $$  __$$\ $$  _____|$$  _____|
$$ |  $$ |$$ |  $$ |$$ /      $$ |  $$ |$$$$$$$$ |\$$$$$$\  \$$$$$$\  
$$ |  $$ |$$ |  $$ |$$ |      $$ |  $$ |$$   ____| \____$$\  \____$$\
$$$$$$$  |\$$$$$$  |\$$$$$$$\ $$ |  $$ |\$$$$$$$\ $$$$$$$  |$$$$$$$  |
\_______/  \______/  \_______|\__|  \__| \_______|\_______/ \_______/
```

A chatbot that functions as a to-do list. Add and remove to-do's, deadlines and events, mark them as done or undone, 
and search for tasks using keywords.

## Adding to-do's

```
todo <description>
```

Adds a simple to-do to the list.

Example: `todo clean room`

Expected output:
```
    __________________________________________________________________________
     Added new task:
       [T][ ] clean room
     You now have 1 task.
    __________________________________________________________________________

```

## Adding deadlines

```
deadline <description> /by <deadline>
```

Adds a task with a deadline to the list.

Example: `deadline write essay /by Wednesday 4pm`

Expected output:
```
    __________________________________________________________________________
     Added new task:
       [D][ ] write essay (by: Wednesday 4pm)
     You now have 2 tasks.
    __________________________________________________________________________

```

## Adding events

```
event <description> /from <start-time> /to <end-time>
```

Adds an event with a start and end time to the list.

Example: `event internal bonding session /from Wednesday 4pm /to 6pm`

Expected output:
```
    __________________________________________________________________________
     Added new task:
       [E][ ] internal bonding session (from: Wednesday 4pm to: 6pm)
     You now have 3 tasks.
    __________________________________________________________________________

```

## Deleting tasks

```
delete <index>
```

Deletes the task at index `index` in the list.

Example: `delete 2`

Expected output:
```
    __________________________________________________________________________
     Okay, deleted this task:
       [D][ ] write essay (by: Wednesday 4pm)
     You now have 2 tasks.
    __________________________________________________________________________

```

## Marking tasks as done

```
mark <index>
```

Marks the undone task at index `index` in the list as done.

Example: `mark 1`

Expected output:
```
    __________________________________________________________________________
     Marked this task as done:
       [T][X] clean room
    __________________________________________________________________________

```

## Marking tasks as undone

```
unmark <index>
```

Marks the done task at index `index` in the list as undone.

Example: `unmark 1`

Expected output:
```
    __________________________________________________________________________
     Unmarked this task:
       [T][ ] clean room
    __________________________________________________________________________

```

## Viewing all tasks

```
list
```

Displays all tasks as an ordered list.

Expected output:
```
    __________________________________________________________________________
     You have the following tasks:
     1. [T][ ] clean room
     2. [E][ ] internal bonding session (from: Wednesday 4pm to: 6pm)
    __________________________________________________________________________

```

## Searching for tasks using a keyword

```
find <keyword>
```

Displays all tasks containing the keyword `keyword`.

Example: `find bonding`

Expected output:
```
    __________________________________________________________________________
     The following tasks were found:
     1. [E][ ] internal bonding session (from: Wednesday 4pm to: 6pm)
    __________________________________________________________________________

```

## Exiting the application.

```
bye
```

Exits the application

Expected output:
```
    __________________________________________________________________________
     Bye, see you.
    __________________________________________________________________________

```