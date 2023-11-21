# Makefile
CC = g++
CFLAGS = -Wall -std=c++17

TARGET = mkindex
SRC_FILES = mkindex.cpp Location.cpp Index.cpp

all: $(TARGET)

$(TARGET): $(SRC_FILES)
	$(CC) $(CFLAGS) $^ -o $@

clean:
	rm -f $(TARGET)
