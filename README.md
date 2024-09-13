# K-Nearest Neighbors Classifier for Digit Recognition

This project implements a K-Nearest Neighbors (KNN) classifier using a custom priority queue for recognizing handwritten digits from the MNIST dataset. The KNN algorithm finds the closest training images to a given test image and predicts its label based on the labels of the nearest neighbors.

## Features

- **Dataset**: MNIST dataset with 60,000 training images and 10,000 test images.
- **Algorithm**: K-Nearest Neighbors (KNN) Classifier.
- **Data Structures**: Custom priority queue implemented using a 4-ary min-heap for efficient retrieval of nearest neighbors.
- **Distance Metric**: Euclidean distance between images.
- **Accuracy**: Achieves an accuracy of 98.67% on 1,000 test images.

## Files in the Project

- `MNIST.java`: The main class that implements the KNN classifier and processes the MNIST dataset.
- `MyPriorityQueue.java`: A custom priority queue class implemented with a 4-ary min-heap for efficient neighbor retrieval.
- `dHeap.java`: The implementation of a d-ary heap used by the priority queue for managing nearest neighbors.
- `HeapInterface.java`: The interface for the heap implementation.
- `dHeapTest.java`: The test file to verify the correctness of the heap implementation.
- `ImageRenderer.java`: A utility to visualize the results using the MNIST dataset.
- `stdlib.jar`: A required library for graphical support.

## How It Works

1. **Data Loading**:
   The MNIST dataset is loaded from `.gz` files into arrays representing the images and labels. Each image is represented as a 1D array of size 784 (28x28 pixels).

2. **KNN Algorithm**:
   - For each test image, the Euclidean distance is calculated between the test image and all training images.
   - A priority queue (min-heap) is used to efficiently find the K closest neighbors.
   - The label of the test image is predicted by taking the most frequent label among the nearest neighbors.

3. **Accuracy Calculation**:
   The accuracy of the classifier is calculated by comparing the predicted labels with the actual labels of the test images.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Sophie-WZR/K-Nearest-Neighbors-Classifier-for-Digit-Recognition.git
   cd K-Nearest-Neighbors-Classifier-for-Digit-Recognition
