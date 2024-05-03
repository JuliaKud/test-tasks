## Task 1

To solve this task we're going to build a graph with all conditions that must be met.

The vertices of the graph will contain letters of the alphabet. If a directed edge 
goes from vertex A to vertex B, it means that letter related to vertex A must be 
before letter related to B in the alphabet.
First of all, letter **a** must be less (in lexicographical order) than any other 
letter that comes first among the words in this list. We mark this in the graph by 
drawing edges from letter **a** to the other first letters. Then **l** must be less 
than all letters that are on the second place in words that start with **a**.  
We do the same thing for **l** in the graph. Similarly, we set the conditions 
for **e** (that must be less than all third letters in words that start with **al**) and **x** (that must be less than all fourth letters in words that start with **ale**).

Now that the graph containing all the necessary conditions for the dictionary is ready, we can compose the dictionary itself, provided that the graph is acyclic (otherwise a cycle would mean that each letter in it must be smaller than the others in this cycle, which is impossible). If there are no cycles in the graph, we can derive the correct order of letters of the alphabet by topological sorting.

This solution works not only for the name Alex, but also for any name we want to place at the top of the list.

## Task 2

Here we accept two .json files and deserializes the JSON data into instances of the 
Bagbun class using the Gson library. Then, since problems and data are stored as 
HashSets, common operations with sets works correctly in our case and generated the 
output data. The resulting sets are serialized into JSON format and are written to 
the specified files.
