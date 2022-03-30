CC = gcc
CFLAGS = -w -Wall -Wextra -pedantic -g -O2 -D_GNU_SOURCE
LIB = `pkg-config --cflags --libs glib-2.0`

SDIR = src
IDIR = include
ODIR = obj
DOC = docs
BINARY = program

DEPS = $(wildcard $(IDIR)/*.h)
SOURCES = $(wildcard $(SDIR)/*.c)
OBJECTS = $(patsubst $(SDIR)/%.c,$(ODIR)/%.o,$(SOURCES))

all: $(BINARY)

$(ODIR)/%.o: $(SDIR)/%.c $(DEPS)
	$(CC) $(CFLAGS) -c -o $@ $< $(LIB) 

$(BINARY): $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o $(BINARY) $(LIB)

run:
	@./$(BINARY)

debug:
	gdb ./$(BINARY)

doc:
	doxygen $(DOC)/Doxyfile

memcheck:
	valgrind --leak-check=full --show-leak-kinds=all ./$(BINARY)

clean:
	rm $(ODIR)/*.o
	rm $(BINARY)
	rm -rf $(DOC)/html