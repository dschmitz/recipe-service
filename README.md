# Recipe Service #
[![Build Status](https://travis-ci.org/dschmitz/recipe-service.svg?branch=master)](https://travis-ci.org/dschmitz/recipe-service)
[![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)


Welcome to recipe-service!

## Build ## 

``` scala
sbt clean compile

sbt test coverage coverageReport

sbt docker:publishLocal

sbt 'gatling:testOnly io.uport.recipe.RecipeServiceSimulation'
```

## Note ##

This software only compiles with scala 2.11 for now as gatling is not yet ready for 2.12!

## Contribution policy ##

Contributions via GitHub pull requests are gladly accepted from their original
author. Along with any pull requests, please state that the contribution is your
original work and that you license the work to the project under the project's
open source license. Whether or not you state this explicitly, by submitting any
copyrighted material via pull request, email, or other means you agree to
license the material under the project's open source license and warrant that
you have the legal authority to do so.

## License ##

This code is open source software licensed under the
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0) license.
