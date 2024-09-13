# K-Nearest-Neighbors Classifier for Digit Recognition

This project implements a K-Nearest Neighbors (KNN) classifier using a custom priority queue and a d-ary heap to classify handwritten digits from the MNIST dataset.

## Overview

The KNN algorithm is used to classify images based on their proximity to a set of labeled training images. This implementation utilizes a 4-ary min-heap for efficient retrieval of the nearest neighbors and calculates Euclidean distances between test and training images. The most common label among the closest neighbors is then predicted as the class of the test image.

## Features

- **Priority Queue**: Implements a priority queue using a 4-ary min-heap to efficiently retrieve the nearest neighbors.
- **K-Nearest Neighbors**: Supports KNN classification with customizable K-values (K=3, 4, 5).
- **Euclidean Distance**: Computes the distance between test and training images using Euclidean distance.
- **MNIST Dataset**: Supports up to 60,000 training images and 10,000 test images from the MNIST dataset.

## Code Structure

### Files

- `HeapInterface.java`: Defines the interface for the heap used in the priority queue.
- `MyPriorityQueue.java`: Implements the priority queue using a 4-ary min-heap.
- `MNIST.java`: The main class that implements the KNN algorithm and handles loading the MNIST dataset.
- `dHeap.java`: Implements the d-ary heap for efficient priority queue operations.
- `ImageRenderer.java`: Renders MNIST images for visualization.
- `dHeapTest.java`: Unit tests for the d-heap implementation.
- `stdlib.jar`: External library used for graphical programming (StdDraw).

### Key Methods

- **`getClosestMatches()`**: Finds the K closest matches from the training dataset for a given test image.
- **`predict()`**: Predicts the label of a test image based on the most common label among the K nearest neighbors.
- **`totalDist()`**: Calculates the Euclidean distance between two images.
- **`loadData()`**: Loads the MNIST dataset from files.

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Sophie-WZR/K-Nearest-Neighbors-Classifier-for-Digit-Recognition.git
2. Download the MNIST dataset and place it in the `data/` directory.

3. Compile and run the `MNIST.java` file:

