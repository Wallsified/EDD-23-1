public class PruebaConjunto {


	public static void main(String[] args) {
		/*
		 * Nota: Lineas que terminan en // son pruebas y/o lineas añadidas.
		 */
		Integer[] i = { 1, 2, 3, 4, 5 };
		Integer[] j = { 2, 4, 5, 6, 7, 8 };
		Integer[] z = { 15, 78, 1, 2, 3, 5, 78, 20, 55, 55, 22, 22, 22 }; //
		Integer[] h = { 1 };//
		Conjunto<Integer> c1 = new Conjunto<>(i);
		Conjunto<Integer> c2 = new Conjunto<>(j);
		System.out.println(c1);

		// ------
		System.out.println("-- START TEST ZONE -- \n");
		Conjunto<Integer> c3 = new Conjunto<>(z);//
		Conjunto<Integer> c4 = new Conjunto<>(h);
		System.out.println(c3.subconjunto(c1));//
		System.out.println(c4.subconjunto(c1));//
		System.out.println(c4.subconjunto(c3));//
		System.out.println(c3.union(c1));
		System.out.println(c3.interseccion(c1));
		System.out.println(c3.diferencia(c1));
		System.out.println(c3.diferenciaSimetrica(c2));
		System.out.println("-- END TEST ZONE -- \n");

		// ----

		Conjunto<Integer> union = (Conjunto<Integer>) (c1.union(c2));
		System.out.println(union); //
		Conjunto<Integer> interseccion = (Conjunto<Integer>) (c1.interseccion(c2));
		System.out.println(interseccion);

	}
}