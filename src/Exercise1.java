public class Exercise1 {
    /**
     * Το πρόγραμμα έχοντας ως datasource έναν πίνακα, τον πίνακα "arr" στη συγκεκριμένη, εντοπίζει και επιστρέφει στον χρήστη
     * τα low και high index του πίνακα, για ένα ακέραιο "KEY" που λαμβάνει ως παράμετρο, αφού όμως πρώτα ταξινομήσει τον πίνακα αυτόν.
     */
    static int[] arr = new int[] {10, 2, 7, 2, 4, 9, 10, 9, 5, 2, 8, 8, 8, 8, 44};
    static final int KEY = 8;  // Αλλάξτε την τιμή της μεταβλητής για αναζήτηση του low και high index οποιουδήποτε στοιχείου του πίνακα επιθυμείτε.

    public static void main(String[] args) {
        printUnsortedFormOfArray(arr);
        System.out.println();

        printMergedSortedArray(arr);
        System.out.println();

        printGetLowAndHighIndexOfArray(arr);
    }

    /**
     *  Η μέθοδος αυτή ταξινομεί τον πίνακα με τη γνωστή μεθοδολογία της "mergeSort".
     * @param arr Η τυπική παράμετρος πίνακα.
     * @param low   Η θέση του πίνακα από όπου θα ξεκινήσει η ταξινόμηση.
     * @param high  Η θέση του πίνακα όπου θα θεωρηθεί το τέλος της ταξινόμησης.
     * @return  Επιστρέφει μια μέθοδο που συνενώνει δύο πίνακες σε έναν, πλέον ολοκληρωμένα ταξινομημένο.
     */
    public static int[] sortedArray(int[] arr, int low, int high) {
        if (high - low <= 0) {
            return new int[] {arr[high]};
        }

        int middle;
        int[] left;
        int[] right;

        middle = (low + high) / 2;

        left = sortedArray(arr, low, middle);
        right = sortedArray(arr, middle + 1, high);

        return mergeSort(left, right);
    }

    /**
     *  Γίνεται η συγχώνευση των δυο ταξινομημένων πινάκων κάνοντας σύγκριση ένας προς ένα τα στοιχεία των πινάκων.
     * @param left  Ο αριστερός πίνακας
     * @param right Ο δεξιός πίνακας
     * @return  Επιστρέφει τον καινούργιο, ταξινομημένο πλέον, πίνακα.
     */
    public static int[] mergeSort(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int i =0;
        int j =0;
        int k =0;

        while ((i < left.length) && (j < right.length)) {
            if (left[i] < right[j]) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        while (i < left.length) {
            merged[k++] = left[i++];
        }

        while (j < right.length) {
            merged[k++] = right[j++];
        }

        return merged;
    }

    /**
     *  Η παρακάτω μέθοδος βρίσκει με τη χρήση δύο "for" το πρώτο index όσο και το τελευταίο index ενός συγκεκριμένου στοιχείου του πίνακα.
     * @param arr   Η τυπική παράμετρος πίνακα.
     * @param key   Τυπική παράμετρος του στοιχείου που μας ενδιαφέρει.
     * @return  Επιστρέφει έναν πίνακα που εμπεριέχει δύο στοιχεία. Το πρώτο αποτελεί το low index του στοιχείου "key",
     * ενώ το δεύτερο αποτελεί το high index του στοιχείου "key".
     */
    public static int[] getLowAndHighIndexOf(int[] arr, int key) {
        int lowIndex = 0;
        int highIndex = 0;
        int[] lowAndHighIndexOfKey = new int[2];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                lowIndex = i + 1;  // +1 for user-friendly output.
                break;
            }
        }
        lowAndHighIndexOfKey[0] = lowIndex;

        for (int i = arr.length -1; i >= 0; i--) {
            if (arr[i] == key) {
                highIndex = i + 1;  // +1 for user-friendly output.
                break;
            }
        }
        lowAndHighIndexOfKey[1] = highIndex;

        return lowAndHighIndexOfKey;
    }

    /**
     *  Απλώς εκτυπώνει τον πίνακα στην αρχική του μορφή, αταξινόμητο.
     * @param arr   Η τυπική παράμετρος πίνακα.
     */
    public static void printUnsortedFormOfArray (int[]arr) {
        System.out.println("Unformated Array: ");
        for (int element : arr) {
           if (element == arr[arr.length -1]) {  // Με τη χρήση του "if" αποφεύγεται το τελευταίο στοιχείο του πίνακα να ακολουθείται από τον χαρακτήρα ",".
               System.out.print(element);
           } else {
               System.out.print(element + ",\t");
           }
        }
    }

    /**
     *  Εκτυπώνει στην κονσόλα τον πίνακα σε ταξινομημένη μορφή.
     * @param arr Η τυπική παράμετρος πίνακα.
     */
    public static void printMergedSortedArray(int[] arr) {
        System.out.println();
        System.out.println("Merge sorted Array: ");

        int[] arrayMerged = sortedArray(arr, 0, arr.length -1);

        for (int item : arrayMerged) {
            if (item == arrayMerged[arrayMerged.length -1]) {
                System.out.print(item);
            } else {
                System.out.print(item + ",\t");
            }
        }
    }

    /**
     * Εκτυπώνει στην κονσόλα το low και high index του στοιχείου "ΚΕΥ".
     * @param arr Η τυπική παράμετρος πίνακα.
     */
    public static void printGetLowAndHighIndexOfArray(int[] arr) {
        int[] arrayMerged = sortedArray(arr, 0, arr.length -1); // Εισάγεται προκειμένου να μπορεί να γίνει η αναζήτηση του index στον ταξινομημένο πλέον πίνακα.
        int[] lowAndHighIndexOfKey = getLowAndHighIndexOf(arrayMerged, KEY);
        System.out.println();
        System.out.println("For key = " + KEY + ":");
        System.out.printf("Low index of Key in the sorted Array: %d, High index of Key in the sorted Array: %d%n",
                lowAndHighIndexOfKey[0], lowAndHighIndexOfKey[1]);  // Output in user-friendly form.
        System.out.printf("Or else (in another form): {%d, %d}", lowAndHighIndexOfKey[0], lowAndHighIndexOfKey[1]);
        System.out.println();
    }
}

