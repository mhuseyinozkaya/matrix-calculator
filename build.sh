#!/usr/bin/bash
#

javapath=$(which java)
javacpath=$(which javac)

if [ -z "$javapath" ]; then
	echo "java bulunamadı, java'nın yüklü olduğuna emin olun!" >&2
	exit 1
else
	if [ -z "$javacpath" ]; then
		echo "javac bulunumadı , java compiler'ın yüklü olduğuna emin olun!" >&2
		exit 2
	fi

	mkdir -p bin/

	if ! javac -d bin src/main/java/org/matrix/matrixcalculator/*.java; then
    		echo "Java dosyaları derlenemedi!" >&2
    		exit 3
	else
		echo "Java dosyaları derlendi, derleme başarılı!"
	fi


fi
