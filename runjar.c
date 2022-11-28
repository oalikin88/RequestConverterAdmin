#include <stdio.h>
#include <process.h>

int main(int argc, char *argv[], char **penv) {
	_execl("bin\\javaw.exe", "bin\\javaw.exe",
	"-jar", ".\\RequestConverterAdmin-1.0-SNAPSHOT.jar", NULL);
	return 0;
}