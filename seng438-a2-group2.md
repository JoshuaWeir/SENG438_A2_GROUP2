**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group: 2      |
|-----------------|
| Jana                |   
| Robert              |   
| Ernest               |   
| Joshua                | 

# 1 Introduction

The goal of this lab is to become famailiar with automated unit testing and using different techniques to develop and write test cases (including using mock objects in the test environment). We will accomplish this by using JUnit in Eclispe to test the SUT with use cases.

The SUT is a modified version of the JFreeChart framework. Test cases will be written for two of the classes withing the framework: Range and DataUtilities. We will be writing tests for all the methods in DataUtilities and five selected methods in Range. These classes have Javadoc specifications, which we will refer to when writing test cases and comparing the expected and actual output values.

Understanding the code documentation is critical when writing unit tests, especialy in a Black-Box environment, as we do not know how the code works, only what it should output. Once the tests are written, we will run the tests on the SUT and record the results.

# 2 Detailed description of unit test strategy

Our testing strategy relied on understanding the Javadoc API specifications. Once each group member was familiar with how the methods operate, we began identifying input partitions. Below is an outline of the partitions we identified for each class.

Range:
Two types of partitioning were considered within the range class. The first corresponded to the range values, and the second corresponded to the method arguments (which argument was the larger range and which arguments were null).

1. Regarding range values, we identified six partitions:
    1. Outside the lower bound of the range.
    2. Incomplete overlap with lower bound of the range.
    3. Complete overlap with the range.
    4. Incomplete overlap with the upper bound of the range.
    5. Outside the upper bound of the range.
    6. Ranges are equal.

2. Regarding the argument values, we identified five partitions:
    1. First argument is higher bound.
    2. First argument is lower bound.
    3. First argument is null and second argumnent is non-null.
    4. First argument is non-null and second argument is null.
    5. Both arguments are null.

DataUtilities:
3. When designing tests for DataUtilites, we identified three partitions:
    1. All positive table values.
    2. All negative table values.
    3. Invalid table arguments.

# 3 Test cases developed
 
The following tables summarize our test cases. They are organized by which test class they are in, and what method each test is for. Refer to Section 2 of this document for the partitions tested by each test.

# 4 How the team work/effort was divided and managed

For this lab, we collaboratively designed our partitions and test cases. When developing the test suite, each group member was assigned a method to write the tests for.

The method breakdown was as follows:
- Ernest: Range.contains; Range.intersects; DataUtilities.createNumberArray
- Jana: Range.getLowerBound; DataUtilities.calculateRowTotal; DataUtilities.getCumulativePercentages
- Joshua: Range.toString; DataUtilities.calculateColumnTotal
- Robert: Range.combine; DataUtilities.CreateNumberArray2D

Regarding the lab report, we answered the questions as a group with Joshua acting as scribe.

# 5 Difficulties encountered, challenges overcome, and lessons learned

The greastest challenge our group faced was familiarizing ourselves with the testing environment. This includes learning how to use mock objects, understanding the functionalities associated with Java annotations, creating meaningful Junit tests, and using Eclispe. 

Another challenge was identifying input partitions and writing test cases for each method. This was challenging, as it is not only difficult to find tests cases, but we also had to manipulate the written tests around the system (like testing around or with exceptions). We learned that the most important thing in developing test cases is understanding the Java Documentation.

# 6 Comments/feedback on the lab itself

This lab was very helpful because it allowed us to gain experinece in identifying input partitions and designing tests cases within these partitions.

