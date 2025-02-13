class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None

max_sheep_count = 0
total_sheep_count = 0
info_data = []
node_list = []
candidate_nodes_set = set()

def dfs(current, sheep, wolf):
    global max_sheep_count, total_sheep_count, info_data, node_list, candidate_nodes_set
    max_sheep_count = max(max_sheep_count, sheep)

    if sheep == total_sheep_count:
        return
    if not candidate_nodes_set:
        return

    current_candidate_nodes = set(candidate_nodes_set)
    for next_node in current_candidate_nodes:
        nextSheep = sheep
        nextWolf = wolf

        if info_data[next_node.data] == 0:
            nextSheep += 1
        else:
            nextWolf += 1

        if nextSheep <= nextWolf:
            continue

        candidate_nodes_set.remove(next_node)
        if next_node.left:
            candidate_nodes_set.add(next_node.left)
        if next_node.right:
            candidate_nodes_set.add(next_node.right)

        dfs(next_node, nextSheep, nextWolf)

        candidate_nodes_set.add(next_node)
        if next_node.left:
            candidate_nodes_set.remove(next_node.left)
        if next_node.right:
            candidate_nodes_set.remove(next_node.right)


def solution(info, edges):
    global max_sheep_count, total_sheep_count, info_data, node_list, candidate_nodes_set
    info_data = info
    n = len(info)

    node_list = [Node(i) for i in range(n)]

    for edge in edges:
        parent = edge[0]
        child = edge[1]
        parentNode = node_list[parent]
        childNode = node_list[child]

        if parentNode.left is None:
            parentNode.left = childNode
        else:
            parentNode.right = childNode

    total_sheep_count = 0
    for i in range(n):
        if info[i] == 0:
            total_sheep_count += 1

    candidate_nodes_set = set()
    rootNode = node_list[0]
    if rootNode.left:
        candidate_nodes_set.add(rootNode.left)
    if rootNode.right:
        candidate_nodes_set.add(rootNode.right)

    max_sheep_count = 0 # Reset max_sheep_count for each call to solution
    dfs(rootNode, 1, 0)
    return max_sheep_count


# Example Usage:
info1 = [0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1]
edges1 = [[0, 1], [1, 2], [1, 4], [0, 8], [8, 7], [9, 10], [9, 11], [4, 3], [6, 5], [4, 6], [8, 9]]
print("Test 1 Result:", solution(info1, edges1))

info2 = [0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0]
edges2 = [[0, 1], [0, 2], [1, 3], [1, 4], [2, 5], [2, 6], [3, 7], [4, 8], [6, 9], [9, 10]]
print("Test 2 Result:", solution(info2, edges2))