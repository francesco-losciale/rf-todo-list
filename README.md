### Project created with `lein reframe template`

### Notes

Cursive does not provide a REPL for shadow-cljs out of the box. You will need to create a mock project file and use lein to download dependencies. Follow the steps here: https://shadow-cljs.github.io/docs/UsersGuide.html#_cursive

# Run

`shadow-cljs compile test && node out/node-tests.js`

`shadow-cljs watch app`