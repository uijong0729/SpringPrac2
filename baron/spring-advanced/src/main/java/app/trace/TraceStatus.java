package app.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로그를 시작할 때의 상태정보
 * 
 * @author kroch
 *
 */
@AllArgsConstructor
@Getter
public class TraceStatus {
	private TraceId traceId;
	/**
	 * 시작시간 정보를 보존해두면 로그 종료시 전체 수행 시간을 구할 수 있다.
	 */
	private Long startTimeMs;
	private String message;
	
	
}
