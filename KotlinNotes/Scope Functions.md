# Scope Functions

There are five of them: `let`, `run`, `with`, `apply`, and`also`.

Basically, these functions do the same: execute a block of code on an object. What's different is how this object becomes available inside the block and what is the result of the whole expression.

| Function | Obeject reference | Return value | Is extension function |
| --- | ---| ---| --|
| `let` | `it` | Lambda result | Yes|
| `run` | `this` | Lambda result | Yes |
| `run` | - | Lambda result | No: called without the context object |
| `with` | `this` | Lambda result | No: takes the context object as an argument |
| `apply` | `this` | Context object | Yes |
| `also` | `it` | Context object | Yes |

- Executing a lambda on non-null objects: `let`

- Introducing an expression as a variable in local scope: `let`

- Object configuration: `apply`

- Object configuration and computing the result: `run`

- Running statements where an expression is required: non-extension `run`

- Additional effects: `also`

- Grouping function calls on an object: `with`


**Context object: this or it**

- as a lambda receiver (`this`) or as a lambda argument (`it`).
- 