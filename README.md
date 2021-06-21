### Project created with `lein reframe template`

### Notes

Cursive does not provide a REPL for shadow-cljs out of the box. You will need to create a mock project file and use lein to download dependencies. Follow the steps here: https://shadow-cljs.github.io/docs/UsersGuide.html#_cursive

Once connected the REPL, run `(shadow/repl :app)` to switch to the shadow-cljs environment

# Run

`shadow-cljs compile test && node out/node-tests.js`

`shadow-cljs watch app`

### TODO

- search `TODO` in the codebase to find gaps that needs to be completed
- repeat the exercise doing TDD and REPL this time. 