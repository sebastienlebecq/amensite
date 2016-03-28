package fr.nations.amen.shared.forumpasteur;

import java.util.Comparator;

public class TitleComparator implements Comparator<Messages>{
	public int compare(Messages o1, Messages o2) {
		if (o1 == o2) {
			return 0;
		}

		// Compare the title columns.
		if (o1 != null) {
			return (o2 != null) ? o1.getTitle().compareTo(
					o2.getTitle()) : 1;
		}
		return -1;
	}
}
