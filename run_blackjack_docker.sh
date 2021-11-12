#!/bin/sh

docker build . -t blackjack-container

docker run -it blackjack-container
