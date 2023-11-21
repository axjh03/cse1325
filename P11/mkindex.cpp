// mkindex.cpp
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include "Index.h"

int main(int argc, char* argv[]) {
    if (argc < 2) {
        std::cerr << "Usage: " << argv[0] << " file1.txt file2.txt ..." << std::endl;
        return 1;
    }

    Index index;

    for (int i = 1; i < argc; ++i) {
        std::ifstream file(argv[i]);
        if (!file.is_open()) {
            std::cerr << "Error opening file: " << argv[i] << std::endl;
            continue;
        }

        std::string line;
        int line_number = 1;

        while (std::getline(file, line)) {
            std::istringstream iss(line);
            std::string word;

            while (iss >> word) {
                index.add_word(word, argv[i], line_number);
            }

            ++line_number;
        }

        file.close();
    }

    // Print the index
    std::cout << index;

    return 0;
}
