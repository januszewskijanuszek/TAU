import math


class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

class PointMath:
    def __init__(self, fistPoint, secondPoint):
        self.fistPoint = fistPoint
        self.secondPoint = secondPoint

    def lenght(self):
        a = pow(self.secondPoint.x - self.fistPoint.x, 2)
        b = pow(self.secondPoint.y - self.fistPoint.y, 2)
        return math.sqrt(a + b)


if __name__ == '__main__':
    point = Point(2, 5)
    point2 = Point(5, 9)
    mat = PointMath(point, point2)
    print(mat.lenght())
