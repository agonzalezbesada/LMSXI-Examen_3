# LMSXI EXAMEN 3ª EVALUACION

# Realiza las siguientes consultas en xquery con el fichero premios.xml (repositorio):
## Devuelve la frase "[nombre] ha ganado el premio de [categoria] en el año [año]"


for $x in doc("premios.xml")/premios_nobel/premios/premio
return concat($x/premiado, ' ha ganado el premio de ', $x/@categoria, ' en el año ', $x/año)

Devolvemos por cada ocurrencia el resultado concatenado usando "Concat"

## Una tabla html [categoria] | [premiado] ordenada de mayor a menor por los [años]
```
<table>
<tr>
<th> Categoria </th>
<th> Premiado </th>
<th> Año </th>
</tr>
{
for $x in doc("premios.xml")/premios_nobel/premios/premio
order by $x/año descending
return
<tbody>
<tr>
<td>{string($x/@categoria)}</td>
<td>{$x/premiado}</td>
<td>{$x/año}</td>
</tr>
</tbody>
}
</table>
```
Retornamos Iterando en formato html los componentes del xml, haciendo especial incapié en el string($x/@categoria), necesario para que se vea el texto de la categoría, ya que es un atributo




## Incluir un nuevo premiado en un nuevo fichero

```
let $nuevosPremios := 
  <premios_nobel>
    <premios>
      {
        for $x in doc("premios.xml")/premios_nobel/premios/premio
        order by $x/año descending
        return
        <premio categoria="{$x/@categoria}">
          <año>{$x/año/text()}</año>
          <premiado>{$x/premiado/text()}</premiado>
          <motivo>{if ($x/motivo) then $x/motivo/text() else ()}</motivo>
        </premio>
      }
      <premio categoria="matematicas">
        <año>2011</año>
        <premiado>Adrian Gonzalez</premiado>
        <motivo>Aprender a sumar</motivo>
      </premio>
    </premios>
  </premios_nobel>

let $rutaArchivo := "G:\Mi unidad\DAW\LMSXI\EJERCICIOS\LMSXI-Examen_3\nuevosPremios.xml"
return
  file:write($rutaArchivo, $nuevosPremios)
```

  Se escribe en un nuevo fichero todas las coincidencias del original usando file:write(), pero introduciendo un nuevo premiado después de recorrer todos los del fichero original

## Realizar un fichero nuevo incluyendo motivos en los que no tienen

```
let $nuevosPremios :=
  <premios_nobel>
    <premios>
      {
        for $x in doc("premios.xml")/premios_nobel/premios/premio
        order by $x/año descending
        return
        if (exists($x/motivo))
        then $x
        else
          <premio categoria="{$x/@categoria}">
            <año>{$x/año}</año>
            <premiado>{$x/premiado}</premiado>
            <motivo>Motivo desconocido</motivo>
          </premio>
      }
    </premios>
  </premios_nobel>

let $rutaArchivo := "G:\Mi unidad\DAW\LMSXI\EJERCICIOS\LMSXI-Examen_3\nuevosPremios2.xml"

return
  file:write($rutaArchivo, $nuevosPremios)
```

  Se escribe en un nuevo fichero todas las coincidencias del original file:write(), pero usando un if para determinar si existe o no un movitvo, y en caso de que no, introducirlo
