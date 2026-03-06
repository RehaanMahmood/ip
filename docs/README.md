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
