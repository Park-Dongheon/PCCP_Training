package pccp기출문제;

public class PCCP기출문제_1번_동영상재생기2 {
	
    /**
     * 주어진 명령들을 처리하여 최종 재생 위치를 "mm:ss" 형식으로 반환합니다.
     *
     * @param video_len 동영상의 전체 길이 ("mm:ss" 형식)
     * @param pos 현재 재생 위치 ("mm:ss" 형식)
     * @param op_Start 오프닝 시작 시각 ("mm:ss" 형식)
     * @param op_End 오프닝 종료 시각 ("mm:ss" 형식)
     * @param commands 사용자로부터 입력된 명령 배열 ("prev" 또는 "next")
     * @return 최종 재생 위치를 "mm:ss" 형식으로 반환
     */
	public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
		int videoLength = toSeconds(video_len);
		int openingStart = toSeconds(op_start);
		int openingEnd = toSeconds(op_end);
		int currentPosition = toSeconds(pos);
		
		currentPosition = isWithin(currentPosition, openingStart, openingEnd) ? openingEnd : currentPosition;
		
		for (String cmd : commands) {
			currentPosition = cmd.equals("prev") ? Math.max(currentPosition - 10, 0) 
							: cmd.equals("next") ? Math.min(currentPosition + 10, videoLength) 
							: currentPosition;
			
			currentPosition = isWithin(currentPosition, openingStart, openingEnd) ? openingEnd : currentPosition;
		}
		
		return formatTime(currentPosition);
	}

    /**
     * "mm:ss" 형식의 시간을 초 단위로 변환합니다.
     *
     * @param time "mm:ss" 형식의 시간 문자열
     * @return 초 단위의 시간
     */
	private int toSeconds(String time) {
		String[] parts = time.split(":");
		return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
	}
	
    /**
     * 초 단위의 시간을 "mm:ss" 형식으로 포맷팅합니다.
     *
     * @param seconds 초 단위의 시간
     * @return "mm:ss" 형식의 시간 문자열
     */
	private String formatTime(int seconds) {
		return String.format("%02d:%02d", seconds / 60, seconds % 60);
	}

    /**
     * 현재 위치가 오프닝 구간 내에 있는지 확인합니다.
     *
     * @param pos 현재 재생 위치 (초 단위)
     * @param start 오프닝 시작 시각 (초 단위)
     * @param end 오프닝 종료 시각 (초 단위)
     * @return 오프닝 구간 내에 있으면 true, 아니면 false
     */
	private boolean isWithin(int pos, int start, int end) {
		return pos >= start && pos <= end;
	}

	public static void main(String[] args) {
		PCCP기출문제_1번_동영상재생기2 s = new PCCP기출문제_1번_동영상재생기2();
		System.out.println(s.solution("34:33", "13:00", "00:55", "02:55", new String[] {"next", "prev"}));
		System.out.println(s.solution("10:55", "00:05", "00:15", "06:55", new String[] {"prev", "next", "next"}));
		System.out.println(s.solution("07:22", "04:05", "00:15", "04:07", new String[] {"next"}));
	}
}
