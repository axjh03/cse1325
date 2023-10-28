#include <iostream>

int main()
{
    char name[100];
    std::cout<<"What's your name? "; // user to enters their name on the same line
    std::cin>>name;
    std::cout<<"Hello, "<<name<<"!"<<std::endl;
}