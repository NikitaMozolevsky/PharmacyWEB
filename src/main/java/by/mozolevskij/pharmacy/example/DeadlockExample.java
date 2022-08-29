package by.mozolevskij.pharmacy.example;

public class DeadlockExample {
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        public synchronized void bow(Friend friend) {
            System.out.println(getName() + " поклонился " + friend.getName());
            friend.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.println(bower.getName() + " поклонился " + this.getName());
        }
    }

    public static void main(String[] args) {
        Friend alphonse = new Friend("Alphonse");
        Friend gaston = new Friend("Gaston");
        new Thread(new Runnable() {
            @Override
            public void run() {
                alphonse.bow(gaston);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                gaston.bow(alphonse);
            }
        }).start();
    }
}
