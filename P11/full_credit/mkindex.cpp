#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <cctype>
#include "Index.h"

using namespace std;

// Function to clean and normalize a word
string cleanWord(const string &rawWord) {
    string word = rawWord;
    size_t start = 0, end = word.length();

    // Remove non-alphanumeric characters from the beginning
    while (start < end && !isalnum(word[start])) {
        ++start;
    }

    // Remove non-alphanumeric characters from the end
    while (start < end && !isalnum(word[end - 1])) {
        --end;
    }

    // Convert the word to lowercase
    transform(word.begin() + start, word.begin() + end, word.begin() + start, ::tolower);

    return word.substr(start, end - start);
}

int main(int argc, char *argv[]) {
    // Check if filenames are provided as command-line arguments
    if (argc < 2) {
        cerr << "Usage: " << argv[0] << " <filename1> <filename2> ..." << endl;
        return 1;
    }

    // Display program title
    cout << "Index" << endl;
    cout << "=====\n" << endl;

    // Create an instance of the Index class to store the word index
    Index wordIndex;

    // Iterate through each provided file
    for (int i = 1; i < argc; ++i) {
        // Open the file
        ifstream inputFile(argv[i]);

        // Check if the file is successfully opened
        if (!inputFile.is_open()) {
            cerr << "Error reading " << argv[i] << endl;
            continue;
        }

        string currentLine;
        int lineNumber = 1;

        // Read each line in the file
        while (getline(inputFile, currentLine)) {
            istringstream lineStream(currentLine);
            string currentWord;

            // Process each word in the line
            while (lineStream >> currentWord) {
                // Clean and normalize the word
                currentWord = cleanWord(currentWord);

                // Add the word to the index along with file information
                wordIndex.add_word(currentWord, argv[i], lineNumber);
            }

            // Move to the next line
            ++lineNumber;
        }

        // Close the file
        inputFile.close();
    }

    // Display the generated word index
    cout << wordIndex;

    return 0;
}
