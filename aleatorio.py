# generador de numeros aleatorios con distribucion exponential

import numpy as np

# definir rng exponencial con semilla 43
def aleatorio(lam):
    scale, size = 4, 10000
    rng = np.random.default_rng(43)
    time_between_calls = rng.exponential(scale=scale, size=size)
    return time_between_calls

# generar 50 numeros aleatorios con distribucion exponencial
lamb = 0.5
for i in range(50):
     print(aleatorio(lamb))

# graficar datos de una DIST POISSON generada en Java con lambda 4 y seed 43
lista = np.array([4.0, 
5.0, 
7.0, 
8.0, 
6.0, 
6.0, 
4.0, 
3.0, 
5.0, 
5.0, 
3.0, 
5.0, 
5.0, 
3.0, 
5.0, 
5.0, 
4.0, 
6.0, 
3.0, 
5.0, 
3.0, 
11.0, 
10.0, 
10.0, 
6.0, 
4.0, 
2.0, 
4.0, 
4.0, 
8.0, 
4.0, 
7.0, 
6.0, 
5.0, 
5.0, 
6.0, 
7.0, 
4.0, 
5.0, 
8.0, 
7.0, 
6.0, 
5.0, 
5.0, 
3.0, 
3.0, 
5.0, 
2.0, 
3.0, 
4.0, 
5.0, 
9.0, 
3.0, 
6.0, 
5.0, 
3.0, 
6.0, 
6.0, 
2.0, 
4.0, 
4.0, 
3.0, 
5.0, 
6.0, 
4.0, 
2.0, 
3.0, 
6.0, 
9.0, 
3.0, 
3.0, 
7.0, 
3.0, 
3.0, 
3.0, 
2.0, 
1.0, 
4.0, 
5.0, 
9.0, 
4.0, 
5.0, 
5.0, 
10.0, 
2.0, 
4.0, 
5.0, 
4.0, 
3.0, 
8.0, 
2.0, 
7.0, 
11.0, 
6.0, 
8.0, 
4.0, 
5.0, 
7.0, 
6.0, 
6.0,   
])

# graficar histograma
import matplotlib.pyplot as plt

plt.hist(lista, bins=10, color='c', edgecolor='black', alpha=0.7)
plt.title('Histograma de numeros aleatorios 1')
plt.xlabel('Intervalos')
plt.ylabel('Frecuencia')
plt.show()

# graficar otro histograma con otros datos DIST EXPONENCIALES
# con lambda 0.5 y seed 43
lista2 = np.array([
     2.60004334363907, 
1.7173226524258922, 
2.1290702507912913, 
6.026887940044877, 
1.0798090335373407, 
0.8669317195417431, 
1.118488918932681, 
0.30038367990880926, 
0.1845280062510756, 
1.356172246613533, 
0.003411289766102616, 
0.5296315973454251, 
0.3307819629297607, 
4.940968553187226, 
0.6827203727957138, 
1.799625933697593, 
2.456446909515698, 
0.19440893997784978, 
0.49603669747689383, 
4.911138570043362, 
2.515598611771179, 
3.1396270449404717, 
4.30147391595526, 
2.768405479556481, 
0.23612616965013158, 
0.024030146236010862, 
6.232634587392892, 
2.1735754567426926, 
3.4513098157387003, 
3.039003558572244, 
5.300461851050517, 
4.256204906448067, 
1.7984772565367855, 
2.3991259166770145, 
0.7416668124787366, 
0.3219769747885474, 
1.5555651060755542, 
3.137743585578779, 
1.583275649794266, 
7.116285481798674, 
0.09827969670257324, 
2.906383094798938, 
1.0859552220250397, 
1.334655978902864, 
0.08850847665075222, 
0.44168391629171133, 
6.082328946638567, 
2.027661942015031, 
1.8301204202470838, 
0.17967804362154582, 
3.7946538800107383, 
2.3581158811175498, 
1.3813469987431082, 
0.6350069126590107, 
1.2852531379520484, 
3.673521891834856, 
1.221877979165766, 
0.5002282753094519, 
5.839051126252962, 
0.17059567484632376, 
0.18375725798538067, 
0.8442336980947008, 
0.24912265854686105, 
3.7725982558521873, 
1.9121511928862012, 
1.109932804916218, 
0.5811017286159164, 
0.5850977888788148, 
1.0646424123400504, 
0.025706063208660524, 
1.3222745379058467, 
1.3321032333994873, 
1.5588000230723633, 
5.466831574218108, 
0.3821888443657512, 
1.9126296278048436, 
0.11103925400701443, 
0.6304831026694406, 
0.6340823012685677, 
1.5822874222035808, 
1.312006403335428, 
2.1049841129700786, 
1.513287567853726, 
3.065688962056694, 
3.284289078965755, 
2.388527619502681, 
3.868797189540152, 
5.705811977503128, 
3.9126234741678236, 
1.605942625079167, 
2.772485832818265, 
0.6096830648595793, 
1.5852543051755734, 
2.207649194922427, 
2.144955031546452, 
1.2281442171389851, 
0.618753165546958, 
0.16758277190755175, 
0.3225925214653338, 
0.9993546098856206, 
])

plt.hist(lista2, bins=10, color='g', edgecolor='black', alpha=0.7)
plt.title('Histograma de numeros aleatorios 1')
plt.xlabel('Intervalos')
plt.ylabel('Frecuencia')
plt.show()