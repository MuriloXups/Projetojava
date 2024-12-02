import matplotlib.pyplot as plt

tamanhos = [100, 1000, 10000, 50000, 100000]
tempos = {
    "Bubble Sort": [0.1, 1.5, 200, 5000, 20000],
    "Selection Sort": [...],
    ...
}

for algoritmo, valores in tempos.items():
    plt.plot(tamanhos, valores, label=algoritmo)

plt.xlabel("Tamanho do Array")
plt.ylabel("Tempo (ms)")
plt.legend()
plt.title("Comparação de Algoritmos de Ordenação")
plt.show()