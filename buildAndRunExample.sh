#!/bin/sh

printf "
   _____ ____    __   ___       _____ _    _____ 
  / ___// __ \  / /  |__ \     / /   | |  / /   |
  \__ \/ / / / / /   __/ /__  / / /| | | / / /| |
 ___/ / /_/ / / /___/ __// /_/ / ___ | |/ / ___ |
/____/\___\_\/_____/____/\____/_/  |_|___/_/  |_|
  
Github : https://github.com/Sassine/sqlschema2java

"

mvn install

cd sqlschema2java-example/

./runSqlSchema2Java.sh