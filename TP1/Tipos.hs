module Tipos where

type Datos = String
type Etiqueta = String
type Nombre = String

insertar :: Ord a => a -> [ a ] -> [ a ]
insertar tema [] = [ tema ]
insertar tema (head:xs)
    | tema < head = tema:head:xs
    | otherwise = head : insertar tema xs

{- TEST
1. Agregar a lista vacía
2. Ordenar como nuevo head
3. Ordenar como nuevo tail
4. Ordenar entre dos elementos
5. Prueba con Nums -}

tiposTest :: [Bool]
tiposTest = [ insertar "Fanky" [] == ["Fanky"], 
    insertar "Cactus" ["Fanky"] == ["Cactus", "Fanky"],
    insertar "Cactus" ["Astros"] == ["Astros", "Cactus"],
    insertar "Cactus" ["Astros", "Fanky"] == ["Astros", "Cactus", "Fanky"],
    insertar 4 [1,11] == [1,4,11]]