#!/bin/bash

# Script for refactoring all ghost images to size given by first two arguments
# $1 -> width
# $2 -> height

cp ../original/ghost* .
for g in ghost*
do
	convert -resize "$1"X"$2"! "$g" "$g"	
done
