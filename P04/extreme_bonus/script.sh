#!/bin/bash

# Change to the extreme_bonus directory
cd /path/to/extreme_bonus

# Run the Java program
java mdi.LibraryManager

# Change to the library directory
cd library

# Remove all .class files in the library directory
rm *.class

# Change back to the extreme_bonus directory
cd ..

# Change to the mdi directory
cd mdi

# Remove all .class files in the mdi directory
rm *.class

# Change back to the extreme_bonus directory
cd ..

