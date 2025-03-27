import random
from collections import namedtuple
from typing import List, Tuple

class PyArtConfig:
    """PyArtConfig class"""
    
    def __init__(self,
                 radius_range: Tuple[int, int] = (10, 100),
                 width_range: Tuple[int, int] = (10, 100),
                 height_range: Tuple[int, int] = (10, 100),
                 color_range: Tuple[int, int] = (0, 255),
                 opacity_range: Tuple[float, float] = (0.0, 1.0)) -> None:
        self.radius_range = radius_range
        self.width_range = width_range
        self.height_range = height_range
        self.color_range = color_range
        self.opacity_range = opacity_range

class RandomShape:
    """RandomShape class"""
    
    def __init__(self, art_config: PyArtConfig, cnt: int, shape_type: str) -> None:
        self.art_config = art_config
        self.cnt = cnt
        self.shape_type = shape_type
        self.x = random.randint(0, 500)
        self.y = random.randint(0, 300)
        self.radius = random.randint(*art_config.radius_range)
        self.rx = random.randint(*art_config.radius_range)
        self.ry = random.randint(*art_config.radius_range)
        self.width = random.randint(*art_config.width_range)
        self.height = random.randint(*art_config.height_range)
        self.red = random.randint(*art_config.color_range)
        self.green = random.randint(*art_config.color_range)
        self.blue = random.randint(*art_config.color_range)
        self.opacity = random.uniform(*art_config.opacity_range)

    def as_Part2_line(self) -> str:
        """as_Part2_line method"""
        
        return f'{self.cnt:>3} {self.shape_type:>3} {self.x:>4} {self.y:>4} {self.radius:>4} {self.rx:>4} {self.ry:>4} {self.width:>4} {self.height:>4} {self.red:>3} {self.green:>3} {self.blue:>3} {self.opacity:>4.1f}'

def main() -> None:
    """main function"""
    
    art_config = PyArtConfig()
    shape_types = ["0", "1", "3"]
    random_shapes = [RandomShape(art_config, cnt, random.choice(shape_types)) for cnt in range(10)]

    header = f'{"CNT":>3} {"SHA":>3} {"X":>4} {"Y":>4} {"RAD":>4} {"RX":>4} {"RY":>4} {"W":>4} {"H":>4} {"R":>3} {"G":>3} {"B":>3} {"OP":>5}'

    with open('output_part_2.txt', 'w') as output_file:
        print(header, file=output_file)

        for shape in random_shapes:
            print(shape.as_Part2_line(), file=output_file)

if __name__ == "__main__":
    main()
