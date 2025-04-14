import math

class Circle:
    def __init__(self, radius):
        self.radius = radius

    # Method to implement logic for getArea()
    def getArea(self):
        area = math.pi * self.radius ** 2
        print(f"Area of Circle: {area:.2f}")
        return area


# Subclass Cylinder derived from the Super class Circle
class Cylinder(Circle):
    def __init__(self, radius, height):
        super().__init__(radius)  # Inherit radius from Circle
        self.height = height

    # Overriding getArea to calculate surface area of cylinder
    def getArea(self):
        base_area = super().getArea()
        lateral_area = 2 * math.pi * self.radius * self.height
        total_area = (2 * base_area) + lateral_area
        print(f"Surface Area of Cylinder: {total_area:.2f}")
        return total_area

    # Method to implement logic for getVolume()
    def getVolume(self):
        volume = math.pi * self.radius ** 2 * self.height
        print(f"Volume of Cylinder: {volume:.2f}")
        return volume


# Created an object for Cylinder class and run methods directly
cyl = Cylinder(4, 6)
cyl.getArea()
cyl.getVolume()
