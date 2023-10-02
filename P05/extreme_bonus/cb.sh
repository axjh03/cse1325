# Change to the extreme_bonus directory
cd /Users/axjh03/Library/CloudStorage/OneDrive-UTArlington/semester/Fall23/CSE1325/github/cse1325/P05/extreme_bonus

# go to library and clean
cd library
rm *.class

# clean mdi
cd ..
cd mdi
rm *.class
cd ..

# Change to the extreme_bonus directory
cd /Users/axjh03/Library/CloudStorage/OneDrive-UTArlington/semester/Fall23/CSE1325/github/cse1325/P05/extreme_bonus

# build the application
ant

# Run the application
java mdi.LibraryManager