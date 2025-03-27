import random
from collections import namedtuple
from typing import List, Tuple

class HtmlDocument:
    """HtmlDocument class"""
    
    def __init__(self, title: str) -> None:
        self.title = title
        self.body = ""

    def add_svg(self, svg: str) -> None:
        """add_svg method"""
        
        self.body += svg

    def save_to_file(self, filename: str) -> None:
        """save_to_file method"""
        
        with open(filename, 'w') as file:
            file.write(f'<html>\n<head>\n   <title>{self.title}</title>\n</head>\n<body>\n{self.body}\n</body>\n</html>')

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
    
    def __init__(self, art_config: PyArtConfig, cnt: int) -> None:
        self.art_config = art_config
        self.cnt = cnt
        self.x = random.randint(0, 500)
        self.y = random.randint(0, 300)
        self.radius = random.randint(*art_config.radius_range)
        self.width = random.randint(*art_config.width_range)
        self.height = random.randint(*art_config.height_range)
        self.red = random.randint(*art_config.color_range)
        self.green = random.randint(*art_config.color_range)
        self.blue = random.randint(*art_config.color_range)
        self.opacity = random.uniform(*art_config.opacity_range)

class SvgCanvas:
    """SvgCanvas class"""
    
    def __init__(self, width: int, height: int) -> None:
        self.width = width
        self.height = height
        self.shapes = []

    def add_shape(self, shape: RandomShape) -> None:
        """add_shape method"""
        
        self.shapes.append(shape)

    def gen_art(self) -> str:
        """gen_art method"""
        
        svg_code = f'<svg width="{self.width}" height="{self.height}">\n'
        for shape in self.shapes:
            svg_code += shape.as_svg() + "\n"
        svg_code += "</svg>"
        return svg_code

class CircleShape(RandomShape):
    """CircleShape class"""
    
    def as_svg(self) -> str:
        """as_svg method"""
        
        return f'<circle cx="{self.x}" cy="{self.y}" r="{self.radius}" fill="rgb({self.red},{self.green},{self.blue})" fill-opacity="{self.opacity:.1f}"></circle>'

class RectangleShape(RandomShape):
    """RectangleShape class"""
    
    def as_svg(self) -> str:
        """as_svg method"""
        
        return f'<rect x="{self.x}" y="{self.y}" width="{self.width}" height="{self.height}" fill="rgb({self.red},{self.green},{self.blue})" fill-opacity="{self.opacity:.1f}"></rect>'
        
        
def create_random_art(config: PyArtConfig, shape_count: int) -> List[RandomShape]:
    """Create random art with a given configuration and shape count."""
    
    shape_types = [CircleShape, RectangleShape]
    shapes = [random.choice(shape_types)(config, cnt) for cnt in range(shape_count)]
    return shapes

def main() -> None:
    """Main function to create random art and save it as HTML files."""
    
    art_config = PyArtConfig()

    shape_counts = [30, 50, 70]
    art_types = [create_random_art(art_config, count) for count in shape_counts]

    for index, art_type in enumerate(art_types):
        html_doc = HtmlDocument(f'a43{index + 1}')
        svg_canvas = SvgCanvas(500, 300)

        for shape in art_type:
            svg_canvas.add_shape(shape)

        svg_art = svg_canvas.gen_art()
        html_doc.add_svg(svg_art)
        html_doc.save_to_file(f'a43{index + 1}.html')

if __name__ == "__main__":
    main()