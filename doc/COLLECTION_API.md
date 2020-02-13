## Collections

Collections are easy to use.

Mistakes are easy to avoid when you read the documentation along with using the code.

For an example, LinkedList:
Implements Serializable, Cloneable, Iterable, Collection, Deque, List, Queue
- Cloneable - you can clone it
- Serializable - you can compress the data?
- Iterable - can iterate over it
- Collection - acts like a collection, has methods of collection
- Deque - a double ended queue, allows you to take things off both ends

Set - there are 3 main types of sets
- TreeSet
- HashSet
- LinkedHashSet

Yes, this number justifies it being an interface. These different types all 
have similar functions.

For the example of LinkedList:
LinkedLists extend several superclasses (a total of 4 levels):
- AbstractSequentialList - ordered
- AbstractList - not ordered
- AbstractCollection - has iterators and size methods
- Object - everything is an object

It makes sense to have utility classes instead of adding that functionality to the collection types 
themselves because it allows you to use generalized methods that work for all implementations of the
parent class. It also enables you to order the class structure with specific methods for different
implementations.

There are overlapping methods such as Collections.addAll and using addAll from a collection object
(such as a linkedList). The guidance for this topic is just looking at the java doc since it gives 
information for how to implement methods.


