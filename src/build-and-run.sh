#!/bin/bash

# On mac or linux execute:
# ./build-and-run.sh

# or to run using input from file input.txt
# ./build-and-run.sh input.txt

javac -cp . com/example/booking/*.java
java -cp . com.example.booking.Booking $1
