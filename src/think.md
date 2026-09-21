ray:
$\begin{pmatrix} x \\ y \\ z \end{pmatrix} = \vec{d} * t + \vec{o}$

or
$\begin{pmatrix} x \\ y \\ z \end{pmatrix} = \begin{pmatrix} x_d * t + x_o \\ y_d * t + y_o \\ z_d * t + z_o \end{pmatrix}$

sphere:
$(x-x_1)^2+(y-y_1)^2+(z-z_1)^2=r^2$

or 
$(\begin{pmatrix} x \\ y \\ z \end{pmatrix} - \vec{c})^2 = r^2$

intersection: <br>
$(\vec{d} * t + \vec{o} - \vec{c})^2 = r^2$ <br>
$(\vec{d} * t + \vec{o} - \vec{c}) (\vec{d} * t + \vec{o} - \vec{c}) = r^2$ <br>
$d^2t^2 + dto - dtc + odt + o^2 - oc - cdt - co + c^2 = r^2$ <br>
$t^2(d^2) + t(do - dc +od - cd) + (o^2 - oc - co + c^2 - r^2) = 0$ <br>

$at^2 + bt + c = 0$ <br>
$a = d^2$ <br>
$b = 2do - 2cd$ <br>
$c = o^2 - 2co + c^2 - r^2$ <br>
discriminant $D = b^2 - 4ac$ <br>
$t = \frac{-b \pm \sqrt{D}}{2a}$



ray tracing process
1. construct a ray
2. check collisions with all spheres, figure out lowest t and the sphere it collided with.
3. construct normal from $\vec{c} - \vec{collision}$
4. normalize vector $\vec{n}$
5. reverse incoming ray $\vec{d} * -1$ and rotate it by $180^\circ$ around the normal vector $\vec{u} = 2\vec{n}(\vec{n}*\vec{x})-\vec{x}$
6. new ray has the intersection point as the origin and u as the direction