package fr.nations.amen.shared.louanges2;

import java.util.Comparator;

import fr.nations.amen.client.louanges2.SearchVideo;

public class DateComparator implements Comparator<SearchVideo>{
	public int compare(SearchVideo o1, SearchVideo o2) {
		if (o1 == o2) {
			return 0;
		}

		// Compare the id columns.
		if (o1 != null) {
			return (o2 != null) ? o1.getDate().compareTo(
					o2.getDate()) : 1;
		}
		return -1;
	}
}
