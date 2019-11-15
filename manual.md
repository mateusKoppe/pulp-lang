# Pulp Lang

## Introduction
Pulp is a language inspirited in Lisp and focused in be legible without any symbol.

## Variables
Pulp is not a typed language, so you will not have to concern about declare variable types.

Pulp has different and cascaded scopes, you can access variables from "parent" scopes but you can't access variables in "child" scopes. 

### Declare
It creates a variable in the scope, you can create it with a initial value or initializing it in null.

```
declare <variable>
declare <variable> to <value>

// E.g
declare spaceship
declare answerOfLifeUniverseAndEverything to 42
```

You cannot redeclare a variable, if you want to change its value use set.

### Set
It changes the value of a variable.

```
set <variable> to <value>

// E.g
set name to "Marvin"
```

## I/O

### Input
Works as an expression, you will use it to allow the user to enter a number in the terminal.

```
<set/declare> <variable> to input

// E.g
declare name to input
```

### Output
It shows in the terminal whatever expression you pass as parameter.

```
show <value>

E.g
declare answer to "Noooooooooooooo"

show "Luke, I`m you father"
show answer
```

## Flow

### If
It will check the condition and if it is truthy it will execute the code inside it.

```
if <condition> do
    ...
done

E.g
if equals firstShooter "Han" do
    show "Han shoots first"
done
```

### Repeat
If you need a loop, you can use repeat, it syntax is really clone to if.

```
repeat if <condition> do
    ...
done

E.g
declare howItLooksLike to input

repeat if different howItLooksLike "fine" do
    show "It doesnt look fine"
    set howItLooksLike to input
done

show "it looks fine"
```

## Math
The mathematic In Pulp, as in Lisp, will declare first the operation you want to do and then receive the params, every math operation in pulp receive two parameters and has explicit order from the right to the left.

The operations are:

|Operation | Example | result |
|- |- | - |
| Division | `division 9 3` | 3 |
| Mod | `mod 9 2` | 1 |
| Multiplication | `multiplication 10 5` | 50 |
| Power | `pow 2 5` | 32 |
| Root | `root 81 2` | 9 |
| Subtraction | `subtraction 20 2` | 18 | 
| Sum | `sum 10 2` | 12 |

You can also use the alias form of `substration (sub)` and `multiplication (mult)`.

### Examples
Bellow are some examples, in math, Lisp and Pulp.

```
// 1 + ( 2 + 3 )
// Lisp -> (+ 1 (+ 2 3))
sum 1 sum 2 3
-> 3

// (2 + ( 8 / 2 )) - 1
// Lisp -> (- (+ 2 (/ 8 2)) 1)
sub sum 2 div 8 2 1
-> 5

// 2 * (4 + 1)
// Lisp -> (* 2 (+ 4 1))
mult 2 sum 4 1
-> 10
```

## Condition
The booleans operators are not so different of the math operations, first declare the operations and then the parameters, every operator, with the only exception of the not operator, receive two params.

The result will be always 1 (if true) and 0 (if false).

The operations are:

|Operation | Example | result |
|- |- | - |
| Equals | `equals 2 1` | 0 |
| Different | `different 2 1` | 1 |
| Greater | `greater 2 1` | 1 |
| Greater Or Equals | `greaterequals 2 1` | 1 |
| Less | `less 2 1` | 0 |
| Less Or Equals | `lessequals 2 1` | 0 |
| And | `and 1 0` | 0 |
| Or | `or 1 0` | 1 |
| Not | `not 0` | 1 |

You can also use the alias form of `different (diff)`.

### Examples
Bellow are some examples.

```
// Lisp -> (equal name "Keanu Reeves")
equals name "Keanu Reeves"

// Lisp -> (and (equal name "Keanu Reeves") (equals movie "SpongeBob"))
and equals name "Keanu Reeves" equals movie "SpongeBob"
```

## Functions
You can declare functions in Pulp, every function has its own scope, it can receive values as parameters and can return value, as any other programming language.

### Declare
Declare a function as a variable.

```
declare <name> to function [params <param1> <...>] do
...
done

E.g
declare greeting to function do
    show "Hello!"
done
```

### Calling functions
Use the operator `call` to run your functions.
```
call <function> [<param1> <...>] end

E.g
call greeting end
```

### Parameters
You can set your function to receive params:
```
declare <name> to function params <param1> <...> do
...
done

E.g
declare greetingUser to function params name do
    show "Welcome"
    show name
done

call greetingUser "Bruce" end
```

### Return
```
declare <name> to function params <param1> <...> do
    return <value>
done

E.g
declare getHalf to function params value do
    return division value 2
done

set money to call getHalf 10 end
```