// 
// Decompiled by Procyon v0.5.36
// 

package mines;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class MinesAdapter extends MouseAdapter {
	@Override
	public void mousePressed(final MouseEvent e) {
		final int x = e.getX();
		final int y = e.getY();
		final int cCol = x / 15;
		final int cRow = y / 15;
		boolean rep = false;
		if (!Board.this.inGame) {
			Board.this.newGame();
			Board.this.repaint();
		}
		if (x < Board.this.cols * 15 && y < Board.this.rows * 15) {
			if (e.getButton() == 3) {
				if (Board.this.field[cRow * Board.this.cols + cCol] > 9) {
					rep = true;
					if (Board.this.field[cRow * Board.this.cols + cCol] <= 19) {
						if (Board.this.mines_left > 0) {
							final int[] field = Board.this.field;
							final int n = cRow * Board.this.cols + cCol;
							field[n] += 10;
							final Board this$0 = Board.this;
							--this$0.mines_left;
							Board.this.statusbar.setText(Integer.toString(Board.this.mines_left));
						} else {
							Board.this.statusbar.setText("No marks left");
						}
					} else {
						final int[] field2 = Board.this.field;
						final int n2 = cRow * Board.this.cols + cCol;
						field2[n2] -= 10;
						final Board this$2 = Board.this;
						++this$2.mines_left;
						Board.this.statusbar.setText(Integer.toString(Board.this.mines_left));
					}
				}
			} else {
				if (Board.this.field[cRow * Board.this.cols + cCol] > 19) {
					return;
				}
				if (Board.this.field[cRow * Board.this.cols + cCol] > 9
						&& Board.this.field[cRow * Board.this.cols + cCol] < 29) {
					final int[] field3 = Board.this.field;
					final int n3 = cRow * Board.this.cols + cCol;
					field3[n3] -= 10;
					rep = true;
					if (Board.this.field[cRow * Board.this.cols + cCol] == 9) {
						Board.this.inGame = false;
					}
					if (Board.this.field[cRow * Board.this.cols + cCol] == 0) {
						Board.this.find_empty_cells(cRow * Board.this.cols + cCol);
					}
				}
			}
			if (rep) {
				Board.this.repaint();
			}
		}
	}
}
