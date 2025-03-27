import os
from collections import namedtuple
from typing import List

class HtmlDocument:
    """HtmlDocument class"""
    
    def __init__(self, title: str):
        self.title = title
        self.body: List[str] = []

    def open_html(self) -> str:
        """Open HTML tag"""
        return "<html>"

    def close_html(self) -> str:
        """Close HTML tag"""
        return "</html>"

    def open_head(self) -> str:
        """Open head tag"""
        return "<head>"

    def close_head(self) -> str:
        """Close head tag"""
        return "</head>"

    def open_body(self) -> str:
        """Open body tag"""
        return "<body>"

    def close_body(self) -> str:
        """Close body tag"""
        return "</body>"

    def write_title(self) -> str:
        """Write title tag"""
        return f'<title>{self.title}</title>'

    def add_to_body(self, content: str):
        """Add content to body"""
        self.body.append(content)

    def generate_html(self) -> str:
        """Generate HTML content"""
        html = []
        html.append(self.open_html())
        html.append(self.open_head())
        html.append(self.write_title())
        html.append(self.close_head())
        html.append(self.open_body())
        html.extend(self.body)
        html.append(self.close_body())
        html.append(self.close_html())
        return '\n'.join(html)

class SvgCanvas:
    """svg_canvas class"""
    def __init__(self, width: int, height: int):
        self.width = width
        self.height = height
        self.shapes: List[str] = []

    def open_svg(self) -> str:
        """Open SVG tag"""
        return f'<svg width="{self.width}" height="{self.height}">'

    def close_svg(self) -> str:
        """Close SVG tag"""
        return '</svg>'

    def add_shape(self, shape: str):
        """Add shape to shapes"""
        self.shapes.append(shape)

    def generate_svg(self) -> str:
        """Generate SVG content"""
        svg = []
        svg.append(self.open_svg())
        svg.extend(self.shapes)
        svg.append(self.close_svg())
        return '\n'.join(svg)

class CircleShape:
    """CircleShape class"""
    
    def __init__(self, cx: int, cy: int, r: int, fill: str, fill_opacity: float):
        self.cx = cx
        self.cy = cy
        self.r = r
        self.fill = fill
        self.fill_opacity = fill_opacity

    def draw(self) -> str:
        """Draw circle"""
        return f'<circle cx="{self.cx}" cy="{self.cy}" r="{self.r}" fill="{self.fill}" fill-opacity="{self.fill_opacity}"></circle>'

class RectangleShape:
    """RectangleShape class"""
    
    def __init__(self, x: int, y: int, width: int, height: int, fill: str, fill_opacity: float):
        self.x = x
        self.y = y
        self.width = width
        self.height = height
        self.fill = fill
        self.fill_opacity = fill_opacity

    def draw(self) -> str:
        """Draw rectangle"""
        return f'<rect x="{self.x}" y="{self.y}" width="{self.width}" height="{self.height}" fill="{self.fill}" fill-opacity="{self.fill_opacity}"></rect>'


def main():
    # Create an HTML document
    html_doc = HtmlDocument("My Art")

    # Create an SVG canvas
    svg_canvas = SvgCanvas(500, 300)

    # Define the circle properties
    Circle = namedtuple("Circle", "cx cy r fill fill_opacity")

    # Add circles to the canvas
    circles = [Circle(50, 50, 50, "rgb(255, 0, 0)", 1.0),
        Circle(150, 50, 50, "rgb(255, 0, 0)", 1.0),
        Circle(250, 50, 50, "rgb(255, 0, 0)", 1.0),
        Circle(350, 50, 50, "rgb(255, 0, 0)", 1.0),
        Circle(450, 50, 50, "rgb(255, 0, 0)", 1.0),
        Circle(50, 250, 50, "rgb(0, 0, 255)", 1.0),
        Circle(150, 250, 50, "rgb(0, 0, 255)", 1.0),
        Circle(250, 250, 50, "rgb(0, 0, 255)", 1.0),
        Circle(350, 250, 50, "rgb(0, 0, 255)", 1.0),
        Circle(450, 250, 50, "rgb(0, 0, 255)", 1.0)]

    for circle in circles:
        svg_canvas.add_shape(CircleShape(circle.cx, circle.cy, circle.r, circle.fill, circle.fill_opacity).draw())

    # Add the SVG canvas to the HTML document body
    html_doc.add_to_body(svg_canvas.generate_svg())

    # Generate the final HTML content
    html_content = html_doc.generate_html()

    # Write the HTML content to a file
    with open('a41.html', 'w') as f:
        f.write(html_content)

if __name__ == "__main__":
    main()