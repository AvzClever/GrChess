package edu.csf.oop.java.chess;

public enum Alliance {
    WHITE{
        @Override
        public String toStr(){
            return "белые";
        }

        @Override
        public String oppositeAlliance() {
            return "чёрные";
        }

        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }
    },
    BLACK{
        @Override
        public String toStr(){
            return "чёрные";
        }

        @Override
        public String oppositeAlliance() {
            return "белые";
        }

        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }
    };

    public abstract boolean isWhite();
    public abstract boolean isBlack();
    public abstract String toStr();
    public abstract String oppositeAlliance();
}

