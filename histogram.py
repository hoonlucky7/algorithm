import matplotlib.pyplot as plt

# 데이터 (Data in English)
categories = ["Brute Force", "Greedy", "Hash"]
counts = [4, 2, 1]

# 막대 그래프 그리기 (Create bar chart)
plt.figure(figsize=(6, 4))
plt.bar(categories, counts, color=['skyblue', 'lightgreen', 'salmon'])
plt.xlabel("Algorithm Type") 
plt.ylabel("Number of Problems") 
plt.title("Distribution of Problem Types")
plt.ylim(0, 5)
plt.show()