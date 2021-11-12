#!/bin/sh

docker build . -f Dockerfile_tests -t blackjack-tests-container

docker run -it blackjack-tests-container

