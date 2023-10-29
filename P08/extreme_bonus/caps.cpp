#include <iostream>
#include <string>
#include <vector>

int main(int argc, char *argv[])
{
    // Writing vector in stack
    std::vector<std::string> caps;

    // Writing vector in heap
    std::vector<std::string> *no_caps = new std::vector<std::string>;

    // Started from 1 because argv[0] is the name of the program and we don't want "./a.out" stored in no_caps vector.
    for(int i=1; i<argc; i++)
    {
       // Direct assignment
       std::string argument(argv[i]);

       if(isupper(argument[0])) // If the first letter is uppercase
       {
            caps.push_back(argument); // Add to the vector caps in stack
       }
       else // else it must be lower case
       {
            no_caps->push_back(argument); // Add to the vector no_caps in heap
       }
    }


    // Sorting

    // Sorting caps in natural order
    sort(caps.begin(), caps.end());

    //  Sort the no_caps vector based first on length (shortest strings first), and within those groups by their natural sort order
    sort(no_caps->begin(), no_caps->end(), [](std::string a, std::string b) {
        if(a.length() == b.length())
        {
            return a < b;
        }
        else
        {
            return a.length() < b.length();
        }
    });

    // After we have added all the arguments to the vectors, we print them
    printf("Capitalized: \n");
    for(int i=0; i<caps.size(); i++)
    {
        std::cout << caps[i] << std::endl;
    }
    printf("\nLower Case: \n");
    for(int i =0; i<no_caps->size(); i++)
    {
        std::cout << no_caps->at(i) << std::endl;
    }

    // delete heap allocated vector
    delete no_caps;
}