max_sheep = 0  # Global variable to store the maximum number of sheep collected
total_sheep = 0  # Global variable to store the total number of sheep

# DFS function to explore the forest and collect sheep
def dfs(node, sheep, wolf, info, graph, next_nodes):
    global max_sheep  # Access the global max_sheep variable

    # Update the maximum number of sheep if the current count is higher
    if sheep > max_sheep:
        max_sheep = sheep
    # If we have collected all the sheep, no need to continue
    if total_sheep == sheep:
        return
    # If there are no more nodes to explore, stop the search
    if not next_nodes:
        return

    # Iterate through a copy of the next_nodes set to avoid concurrent modification
    for next_node in set(next_nodes):
        next_sheep = sheep  # Number of sheep after visiting the next node
        next_wolf = wolf  # Number of wolves after visiting the next node

        # Check if the next node is a sheep or a wolf
        if info[next_node] == 0:
            next_sheep += 1  # If it's a sheep, increment the sheep count
        else:
            next_wolf += 1  # If it's a wolf, increment the wolf count

        # If the number of sheep is not greater than the number of wolves, we cannot proceed further
        if next_sheep <= next_wolf:
            continue

        # Remove the current node from the set of next possible nodes
        next_nodes.remove(next_node)

        # Add the children of the current node to the set of next possible nodes
        for neighbor in graph[next_node]:
            next_nodes.add(neighbor)

        # Recursively call DFS for the next node
        dfs(next_node, next_sheep, next_wolf, info, graph, next_nodes)

        # Backtrack: Add the current node back to the set of next possible nodes
        next_nodes.add(next_node)

        # Backtrack: Remove the children of the current node from the set
        for neighbor in graph[next_node]:
            next_nodes.remove(neighbor)


# Function to solve the sheep and wolf problem
def solution(info, edges):
    global max_sheep, total_sheep  # Access the global variables

    # Reset the global variables
    max_sheep = 0
    total_sheep = sum(1 for i in info if i == 0)  # Calculate the total number of sheep

    n = len(info)  # Number of nodes in the forest

    # Build the graph (adjacency list)
    graph = [[] for _ in range(n)]
    for u, v in edges:
        graph[u].append(v)  # Add an edge from node u to node v

    # Initialize the set of next possible nodes (children of the root node)
    next_nodes = set(graph[0])

    # Start the DFS traversal from the root node (node 0)
    dfs(0, 1, 0, info, graph, next_nodes)

    # Return the maximum number of sheep collected
    return max_sheep


# Test cases
info1 = [0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1]
edges1 = [[0, 1], [1, 2], [1, 4], [0, 8], [8, 7], [9, 10], [9, 11], [4, 3], [6, 5], [4, 6], [8, 9]]
print("Test Case 1:", solution(info1, edges1))  # Expected Output: 5

info2 = [0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0]
edges2 = [[0, 1], [0, 2], [1, 3], [1, 4], [2, 5], [2, 6], [3, 7], [4, 8], [6, 9], [9, 10]]
print("Test Case 2:", solution(info2, edges2))  # Expected Output: 5